package lexer;

import java.util.regex.Pattern;

public class Tokens {
    public static final Token TEST = new Token(Pattern.compile("test\b"), "TOKEN_TEST");
    public static final Token IDENTIFIER = new Token(Pattern.compile("[a-zA-Z_]\\w*\\b"), "TOKEN_IDENTIFIER");
    public static final Token CONSTANT = new Token(Pattern.compile("[0-9]+\\b"), "TOKEN_CONSTANT");
    public static final Token INT = new Token(Pattern.compile("int\\b"), "TOKEN_INT");
    public static final Token VOID = new Token(Pattern.compile("void\\b"), "TOKEN_VOID");
    public static final Token RETURN = new Token(Pattern.compile("return\\b"), "TOKEN_RETURN");
    public static final Token OPEN_PARENTHESIS = new Token(Pattern.compile("\\("), "TOKEN_OPEN_PARENTHESIS");
    public static final Token CLOSE_PARENTHESIS = new Token(Pattern.compile("\\)"), "TOKEN_CLOSE_PARENTHESIS");
    public static final Token OPEN_BRACE = new Token(Pattern.compile("\\{"), "TOKEN_OPEN_BRACE");
    public static final Token CLOSE_BRACE = new Token(Pattern.compile("\\}"), "TOKEN_CLOSE_BRACE");
    public static final Token SEMICOLON = new Token(Pattern.compile(";"), "TOKEN_SEMICOLON");

    private Tokens() throws Exception {
        throw new Exception("Not Allowed");
    }
}
