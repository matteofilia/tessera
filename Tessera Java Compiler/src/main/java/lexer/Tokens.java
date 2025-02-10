package lexer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokens {

    private static boolean loaded = false;
    private static final ArrayList<Token> allTokens = new ArrayList<>();

    public static void init() {
        // NOTE: order is important here
        Tokens.add(new Token(Pattern.compile("test\b"), "TOKEN_TEST"));
        Tokens.add(new Token(Pattern.compile("[0-9]+\\b"), "TOKEN_CONSTANT"));
        Tokens.add(new Token(Pattern.compile("int\\b"), "TOKEN_INT"));
        Tokens.add(new Token(Pattern.compile("void\\b"), "TOKEN_VOID"));
        Tokens.add(new Token(Pattern.compile("return\\b"), "TOKEN_RETURN"));
        Tokens.add(new Token(Pattern.compile("\\("), "TOKEN_OPEN_PARENTHESIS"));
        Tokens.add(new Token(Pattern.compile("\\)"), "TOKEN_CLOSE_PARENTHESIS"));
        Tokens.add(new Token(Pattern.compile("\\{"), "TOKEN_OPEN_BRACE"));
        Tokens.add(new Token(Pattern.compile("\\}"), "TOKEN_CLOSE_BRACE"));
        Tokens.add(new Token(Pattern.compile(";"), "TOKEN_SEMICOLON"));
        Tokens.add(new Token(Pattern.compile("[a-zA-Z_]\\w*\\b"), "TOKEN_IDENTIFIER"));
    }

    public static void add(Token token) {
        allTokens.add(token);
    }

    public static ArrayList<Token> getAll() {
        if (!loaded) {
            init();
            loaded = true;
        }

        return allTokens;
    }

    public static ArrayList<Token> checkMatches(String text) throws LexerException {
        ArrayList<Token> list = new ArrayList<>();
        System.out.println("Checking match: "+text);

        // Check all tokens for match
        int index = 0;
        Token longestMatch = null;
        int maxLength = 0;

        while (index < text.length()) {
            if (Character.isWhitespace(text.charAt(index))) {
                index++;
                continue;
            }

            for (Token token : getAll()) {
                Matcher matcher = token.getPattern().matcher(text);

                System.out.println("Finding From Index: "+index);
                if (matcher.find(index) && matcher.start() == index) {
                    System.out.println("Start = " + matcher.start());
                    System.out.println("Index = " + index);
                    System.out.println("MaxLength = " + maxLength);

                    int length = matcher.end() - matcher.start();
                    System.out.println("Length: "+length);

                    if (length > maxLength) {
                        System.out.println("Match found!"+" ("+ token.getName()+")");

                        longestMatch = token;
                        maxLength = length;
                    }
                }
            }

            if (longestMatch != null) {
                list.add(longestMatch);
                index = index + maxLength;

                longestMatch = null;
                maxLength = 0;
            } else {
                // No match
                throw new LexerException();
            }
        }

        return list;
    }

    public static Token findLongestMatch(String text) {
        int maxLength = 0;
        Token returnToken = null;

        // Check all tokens for match
        for (Token token : allTokens) {
            Matcher matcher = token.getPattern().matcher(text);
            if (matcher.find()) {
                return token;
            }
        }

        // No matching token found
        return returnToken;
    }

    private Tokens() throws Exception {
        throw new Exception("Not Allowed");
    }
}
