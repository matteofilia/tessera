package org.tessera_lang;

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
        Parser.expect(list, LexerTokens.TOKEN_CONSTANT);
        this.setValueString(Parser.expectRawValue(list));

        return this;
    }

    @Override
    public String getIdentifier() {
        return LexerTokens.TOKEN_CONSTANT;
    }
}
