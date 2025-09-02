package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;
import org.tessera_lang.lexer.LexerTokens;

import java.util.ArrayList;

public class ParserASTNodeReturn extends ParserASTNodePlaceholder {

    private ParserASTNode returnValue;

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list, RunConfiguration runConfig) throws ParserException {
        expect(list, LexerTokenIdentifier.TOKEN_RETURN);
        returnValue = parse(list, runConfig);
        expect(list, LexerTokenIdentifier.TOKEN_SEMICOLON);

        return this;
    }
}
