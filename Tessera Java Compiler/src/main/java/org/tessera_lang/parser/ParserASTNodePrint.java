package org.tessera_lang.parser;

import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;
import org.tessera_lang.lexer.LexerTokens;

import java.util.ArrayList;

public class ParserASTNodePrint extends ParserASTNode {

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        // TODO
        throw new ParserException();
    }

    @Override
    public LexerTokenIdentifier getIdentifier() {
        return LexerTokenIdentifier.TOKEN_PRINT;
    }
}
