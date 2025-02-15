package org.tessera_lang.lexer;

public enum LexerTokenIdentifier {

    // Test Tokens For Debug
    TOKEN_TEST("TEST"),

    // Data Type Tokens
    TOKEN_INTEGER("INTEGER"),

    // Special Keyword Tokens
    TOKEN_FUNCTION("FUNCTION"),
    TOKEN_INT("INT"),
    TOKEN_VOID("VOID"),
    TOKEN_RETURN("RETURN"),

    // Control Tokens
    TOKEN_IF("IF"),
    TOKEN_THEN("THEN"),
    TOKEN_ELIF("ELIF"),
    TOKEN_ELSE("ELSE"),

    // Syntax Tokens
    TOKEN_OPEN_PARENTHESIS("OPEN_PARENTHESIS"),
    TOKEN_CLOSE_PARENTHESIS("CLOSE_PARENTHESIS"),
    TOKEN_OPEN_BRACE("OPEN_BRACE"),
    TOKEN_CLOSE_BRACE("CLOSE_BRACE"),
    TOKEN_OPEN_SQUARE_BRACE("OPEN_SQUARE_BRACE"),
    TOKEN_CLOSE_SQUARE_BRACE("CLOSE_SQUARE_BRACE"),

    // More Syntax Tokens
    TOKEN_SEMICOLON("SEMICOLON"),
    TOKEN_COLON("COLON"),

    // Identifier Tokens
    TOKEN_IDENTIFIER("IDENTIFIER"),

    // Special Function Tokens
    TOKEN_PRINT("PRINT"),
    TOKEN_EXIT("EXIT"),

    // Equality Tokens
    TOKEN_EQUALS("EQUALS"),
    TOKEN_DOUBLE_EQUALS("DOUBLE_EQUALS"),
    TOKEN_TRIPLE_EQUALS("TRIPLE_EQUALS"),

    // Operation Tokens
    TOKEN_ADD("ADD"),
    TOKEN_SUBTRACT("SUBTRACT"),
    TOKEN_MULTIPLY("MULTIPLY"),
    TOKEN_DOUBLE_MULTIPLY("DOUBLE_MULTIPLY"),
    TOKEN_DIVIDE("DIVIDE"),
    TOKEN_MODULUS("MODULUS"),

    // Boolean Logic Tokens
    TOKEN_AND("AND"),
    TOKEN_OR("OR"),
    TOKEN_NOT("NOT");

    private static final String PREFIX = "t_";

    private String name;

    LexerTokenIdentifier(String name) {
        this.name = PREFIX + name;
    }

    public String getName() {
        return this.name;
    }
}
