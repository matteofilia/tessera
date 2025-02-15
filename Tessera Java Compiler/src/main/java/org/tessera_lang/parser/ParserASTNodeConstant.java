package org.tessera_lang.parser;

import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;
import org.tessera_lang.lexer.LexerTokens;

import java.util.ArrayList;

public class ParserASTNodeConstant extends ParserASTNode {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValueString(String value) {
        Integer.valueOf(value);
    }

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        Parser.expect(list, LexerTokenIdentifier.TOKEN_INTEGER);
        this.setValueString(Parser.expectRawValue(list));

        return this;
    }

    @Override
    public LexerTokenIdentifier getIdentifier() {
        return LexerTokenIdentifier.TOKEN_INTEGER;
    }
}
