package org.tessera_lang;

public class ParserASTNodeAddition extends ParserASTNode {

    @Override
    public String getIdentifier() {
        return LexerTokens.TOKEN_ADD;
    }
}
