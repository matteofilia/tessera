package org.tessera_lang.parser;

import org.tessera_lang.lexer.LexerToken;

import java.util.ArrayList;

public class ParserASTNodeBinaryOperation extends ParserASTNode {
    public enum OperatorType {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        MODULUS,
        POWER,

        AND,
        OR
    }

    private OperatorType operatorType;

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        return this;
    }
}
