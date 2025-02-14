package org.tessera_lang;

import java.util.regex.Pattern;

public class LexerValue extends LexerToken {

    private String value;

    public LexerValue(String value) {
        super(null, value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
