package org.tessera_lang.parser;

import org.tessera_lang.lexer.LexerToken;

import java.util.ArrayList;

public class ParserASTNodeBinaryOperation extends ParserASTNode {
    public static final int OPERATOR_ADD = 0;
    public static final int OPERATOR_SUBTRACT = 1;
    public static final int OPERATOR_MULTIPLY = 2;
    public static final int OPERATOR_DIVIDE = 3;

    private int operatorType;

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        // TODO
        return this;
    }
}
