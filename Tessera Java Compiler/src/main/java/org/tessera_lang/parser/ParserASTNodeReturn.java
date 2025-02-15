package org.tessera_lang.parser;

import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;
import org.tessera_lang.lexer.LexerTokens;

import java.util.ArrayList;

public class ParserASTNodeReturn extends ParserASTNode {

    private ParserASTNode returnValue;

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        expect(list, LexerTokenIdentifier.TOKEN_RETURN);
        returnValue = parse(list);
        expect(list, LexerTokenIdentifier.TOKEN_SEMICOLON);

        return this;
    }

    @Override
    public LexerTokenIdentifier getIdentifier() {
        return LexerTokenIdentifier.TOKEN_RETURN;
    }
}
