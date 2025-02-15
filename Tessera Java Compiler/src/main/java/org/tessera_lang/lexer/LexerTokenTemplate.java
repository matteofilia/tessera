package org.tessera_lang.lexer;

import java.util.regex.Pattern;

public class LexerTokenTemplate {
    private Pattern pattern;
    private LexerTokenIdentifier identifier;
    private boolean shouldHaveValue;

    public LexerTokenTemplate(Pattern pattern, LexerTokenIdentifier identifier){
        this.pattern = pattern;
        this.identifier = identifier;
        this.shouldHaveValue = false;
    }

    public LexerTokenTemplate(Pattern pattern, LexerTokenIdentifier identifier, boolean shouldHaveValue) {
        this.pattern = pattern;
        this.identifier = identifier;
        this.shouldHaveValue = shouldHaveValue;
    }

    public Pattern getPattern() {
        return this.pattern;
    }

    public LexerTokenIdentifier getIdentifier() {
        return this.identifier;
    }

    public boolean shouldHaveValue() {
        return shouldHaveValue;
    }
}
