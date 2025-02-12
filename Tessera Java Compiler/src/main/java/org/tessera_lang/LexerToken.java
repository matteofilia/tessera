package org.tessera_lang;

import java.util.regex.Pattern;

public class LexerToken {
    // TODO: make this less sloppy

    private Pattern pattern;
    private String id;
    private boolean hasValue;

    public LexerToken(Pattern pattern, String id){
        this.pattern = pattern;
        this.id = id;
        this.hasValue = false;
    }

    public LexerToken(Pattern pattern, String id, boolean hasValue) {
        this.pattern = pattern;
        this.id = id;
        this.hasValue = hasValue;
    }

    public Pattern getPattern() {
        return this.pattern;
    }

    public String getId() {
        return id;
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public boolean hasValue() {
        return hasValue;
    }
}
