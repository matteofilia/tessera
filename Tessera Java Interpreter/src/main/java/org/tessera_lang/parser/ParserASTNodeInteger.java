package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.interpreter.InterpreterStackIdentifierContext;
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
    public ParserASTNode parse(ArrayList<LexerToken> list, RunConfiguration runConfig) throws ParserException {
        Parser.expect(list, LexerTokenIdentifier.TOKEN_INTEGER, runConfig);
        this.setValueString(Parser.expectRawValue(list, runConfig));

        return this;
    }

    @Override
    public boolean hasValue(InterpreterStackIdentifierContext context) {
        return true;
    }

    @Override
    public boolean hasType(InterpreterStackIdentifierContext context) {
        return true;
    }

    @Override
    public InterpreterType getType(InterpreterStackIdentifierContext context) {
        return InterpreterType.INTEGER;
    }

    @Override
    public InterpreterValue getValue(InterpreterStackIdentifierContext context) {
        return new InterpreterValueInt(value);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
