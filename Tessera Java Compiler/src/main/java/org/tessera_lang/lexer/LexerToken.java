package org.tessera_lang.lexer;

public class LexerToken {
    private LexerTokenIdentifier identifier;
    private String value;

    // For Debug and Error Handling
    private String originLine;
    private int originRow;
    private int originColumn;

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

    public String getOriginLine() {
        return originLine;
    }

    public void setOriginLine(String originLine) {
        this.originLine = originLine;
    }

    public int getOriginRow() {
        return originRow;
    }

    public void setOriginRow(int originRow) {
        this.originRow = originRow;
    }

    public int getOriginColumn() {
        return originColumn;
    }

    public void setOriginColumn(int originColumn) {
        this.originColumn = originColumn;
    }
}
