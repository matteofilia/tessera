package org.tessera_lang.parser;

import org.tessera_lang.interpreter.InterpreterType;
import org.tessera_lang.interpreter.InterpreterValue;
import org.tessera_lang.interpreter.InterpreterValueInt;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

import java.util.ArrayList;

public class ParserASTNodeInteger extends ParserASTNodePlaceholder {

    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    public void setValueString(String value) {
        Integer.valueOf(value);
    }

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        Parser.expect(list, LexerTokenIdentifier.TOKEN_INTEGER);
        this.setValueString(Parser.expectRawValue(list));

        return this;
    }

    @Override
    public boolean hasValue() {
        return true;
    }

    @Override
    public boolean hasType() {
        return true;
    }

    @Override
    public InterpreterType getType() {
        return InterpreterType.INTEGER;
    }

    @Override
    public InterpreterValue getValue() {
        return new InterpreterValueInt(value);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
