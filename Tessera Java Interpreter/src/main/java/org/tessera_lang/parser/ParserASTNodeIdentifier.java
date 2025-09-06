package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.interpreter.InterpreterStackIdentifierContext;
import org.tessera_lang.interpreter.InterpreterType;
import org.tessera_lang.interpreter.InterpreterValue;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

import java.util.ArrayList;

public class ParserASTNodeIdentifier extends ParserASTNodePlaceholder {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public void setValueString(String value) {
        this.value = value;
    }

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list, RunConfiguration runConfig) throws ParserException {
        Parser.expect(list, LexerTokenIdentifier.TOKEN_IDENTIFIER, runConfig);
        this.setValueString(Parser.expectRawValue(list, runConfig));

        return this;
    }

    @Override
    public boolean hasValue(InterpreterStackIdentifierContext context) {
        return context.has(this.value);
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
        return context.get(value);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
