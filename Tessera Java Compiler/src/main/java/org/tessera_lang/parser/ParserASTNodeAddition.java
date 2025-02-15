package org.tessera_lang.parser;

import org.tessera_lang.lexer.LexerTokenIdentifier;
import org.tessera_lang.lexer.LexerTokens;

public class ParserASTNodeAddition extends ParserASTNode {

    @Override
    public LexerTokenIdentifier getIdentifier() {
        return LexerTokenIdentifier.TOKEN_ADD;
    }
}
