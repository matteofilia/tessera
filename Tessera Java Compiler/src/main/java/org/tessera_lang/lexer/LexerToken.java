package org.tessera_lang.lexer;

public class LexerToken {
    private LexerTokenIdentifier identifier;
    private String value;

    public LexerToken(LexerTokenIdentifier identifier){
        this.identifier = identifier;
    }

    public LexerToken(LexerTokenIdentifier identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LexerTokenIdentifier getIdentifier() {
        return identifier;
    }

    public boolean hasValue() {
        return value != null;
    }
}
