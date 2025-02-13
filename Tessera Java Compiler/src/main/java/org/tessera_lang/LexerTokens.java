package org.tessera_lang;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexerTokens {


    public static final String TOKEN_CONSTANT = "TOKEN_CONSTANT";
    public static final String TOKEN_INT = "TOKEN_INT";
    public static final String TOKEN_VOID = "TOKEN_VOID";
    public static final String TOKEN_RETURN = "TOKEN_RETURN";
    public static final String TOKEN_OPEN_PARENTHESIS = "TOKEN_OPEN_PARENTHESIS";
    public static final String TOKEN_CLOSE_PARENTHESIS = "TOKEN_CLOSE_PARENTHESIS";
    public static final String TOKEN_OPEN_BRACE = "TOKEN_OPEN_BRACE";
    public static final String TOKEN_CLOSE_BRACE = "TOKEN_CLOSE_BRACE";
    public static final String TOKEN_SEMICOLON = "TOKEN_SEMICOLON";
    public static final String TOKEN_IDENTIFIER = "TOKEN_IDENTIFIER";

    private static boolean loaded = false;
    private static final ArrayList<LexerToken> allTokens = new ArrayList<>();

    public static void init() {
        // NOTE: order is important here
        LexerTokens.add(new LexerToken(Pattern.compile("test\b"), "TOKEN_TEST"));
        LexerTokens.add(new LexerToken(Pattern.compile("[0-9]+\\b"), TOKEN_CONSTANT, true));
        LexerTokens.add(new LexerToken(Pattern.compile("int\\b"), TOKEN_INT));
        LexerTokens.add(new LexerToken(Pattern.compile("void\\b"), TOKEN_VOID));
        LexerTokens.add(new LexerToken(Pattern.compile("return\\b"), TOKEN_RETURN));
        LexerTokens.add(new LexerToken(Pattern.compile("\\("), TOKEN_OPEN_PARENTHESIS));
        LexerTokens.add(new LexerToken(Pattern.compile("\\)"), TOKEN_CLOSE_PARENTHESIS));
        LexerTokens.add(new LexerToken(Pattern.compile("\\{"), TOKEN_OPEN_BRACE));
        LexerTokens.add(new LexerToken(Pattern.compile("\\}"), TOKEN_CLOSE_BRACE));
        LexerTokens.add(new LexerToken(Pattern.compile(";"), TOKEN_SEMICOLON));
        LexerTokens.add(new LexerToken(Pattern.compile("[a-zA-Z_]\\w*\\b"), TOKEN_IDENTIFIER, true));
    }

    public static void add(LexerToken token) {
        allTokens.add(token);
    }

    public static ArrayList<LexerToken> getAll() {
        if (!loaded) {
            init();
            loaded = true;
        }

        return allTokens;
    }

    public static ArrayList<LexerToken> checkMatches(String line) throws LexerException {
        ArrayList<LexerToken> list = new ArrayList<>();
        if (Main.BE_VERBOSE) System.out.println("Checking match: "+line);

        // Check all tokens for match
        int index = 0;
        LexerToken longestMatch = null;
        LexerValue longestMatchValue = null;
        int maxLength = 0;

        while (index < line.length()) {
            if (Character.isWhitespace(line.charAt(index))) {
                index++;
                continue;
            } else if (line.charAt(index) == '#') {
                // Comment, ignore remaining input
                index = line.length();
                continue;
            }

            for (LexerToken token : getAll()) {
                Matcher matcher = token.getPattern().matcher(line);

                if (Main.BE_VERY_VERBOSE) System.out.println("Finding From Index: "+index);
                if (matcher.find(index) && matcher.start() == index) {
                    if (Main.BE_VERY_VERBOSE) System.out.println("Start = " + matcher.start());
                    if (Main.BE_VERY_VERBOSE)System.out.println("Index = " + index);
                    if (Main.BE_VERY_VERBOSE) System.out.println("MaxLength = " + maxLength);

                    int length = matcher.end() - matcher.start();
                    if (Main.BE_VERY_VERBOSE) System.out.println("Length: "+length);

                    if (length > maxLength) {
                        if (Main.BE_VERBOSE) System.out.println("Match found!"+" ("+ token.getId()+")");

                        longestMatch = token;
                        if (longestMatch.hasValue()) {
                            longestMatchValue = new LexerValue(line.substring(matcher.start(), matcher.end()));
                            if (Main.BE_VERBOSE) System.out.println("Value token: ("+longestMatchValue.getId()+")");
                        }
                        maxLength = length;
                    }
                }
            }

            if (longestMatch != null) {
                list.add(longestMatch);
                if (longestMatchValue != null) {
                    list.add(longestMatchValue);
                }
                index = index + maxLength;

                longestMatch = null;
                longestMatchValue = null;
                maxLength = 0;
            } else {
                // No match
                throw new LexerException();
            }
        }

        return list;
    }

    private LexerTokens() throws Exception {
        throw new Exception("Not Allowed");
    }
}
