package lexer;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Tokens {


    private static final ArrayList<Token> allTokens = new ArrayList<>();

    public static void init() {
        Tokens.add(new Token(Pattern.compile("test\b"), "TOKEN_TEST"));
        Tokens.add(new Token(Pattern.compile("[a-zA-Z_]\\w*\\b"), "TOKEN_IDENTIFIER"));
        Tokens.add(new Token(Pattern.compile("[0-9]+\\b"), "TOKEN_CONSTANT"));
        Tokens.add(new Token(Pattern.compile("int\\b"), "TOKEN_INT"));
        Tokens.add(new Token(Pattern.compile("void\\b"), "TOKEN_VOID"));
        Tokens.add(new Token(Pattern.compile("return\\b"), "TOKEN_RETURN"));
        Tokens.add(new Token(Pattern.compile("\\("), "TOKEN_OPEN_PARENTHESIS"));
        Tokens.add(new Token(Pattern.compile("\\)"), "TOKEN_CLOSE_PARENTHESIS"));
        Tokens.add(new Token(Pattern.compile("\\{"), "TOKEN_OPEN_BRACE"));
        Tokens.add(new Token(Pattern.compile("\\}"), "TOKEN_CLOSE_BRACE"));
        Tokens.add(new Token(Pattern.compile(";"), "TOKEN_SEMICOLON"));
    }

    public static void add(Token token) {
        allTokens.add(token);
    }

    public static ArrayList<Token> getAll() {
        return allTokens;
    }

    public static Token checkMatch(String text) {
        // Check all tokens for match
        for (Token token : allTokens) {
            if (token.getPattern().matcher(text).find()) {
                return token;
            }
        }

        // No matching token found
        return null;
    }

    private Tokens() throws Exception {
        throw new Exception("Not Allowed");
    }
}
