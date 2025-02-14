package org.tessera_lang;

import java.util.ArrayList;

public class ParserASTNodeReturn extends ParserASTNode {

    private ParserASTNode returnValue;

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        expect(list, LexerTokens.TOKEN_RETURN);
        returnValue = parse(list);
        expect(list, LexerTokens.TOKEN_SEMICOLON);

        return this;
    }
}
