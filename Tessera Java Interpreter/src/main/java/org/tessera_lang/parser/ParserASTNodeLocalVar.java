package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.interpreter.InterpreterStackIdentifierContext;
import org.tessera_lang.interpreter.InterpreterValueInt;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

import java.util.ArrayList;

public class ParserASTNodeLocalVar extends ParserASTNodePlaceholder {

    private String identifier;
    private String value;

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list, RunConfiguration runConfig) throws ParserException {
        Parser.expect(list, LexerTokenIdentifier.TOKEN_LOCAL_VAR, runConfig);
        this.identifier = Parser.expectRawValue(list, runConfig);
        Parser.expect(list, LexerTokenIdentifier.TOKEN_EQUALS, runConfig);
        this.value = Parser.expectRawValue(list, runConfig);
        Parser.weakExpect(list, LexerTokenIdentifier.TOKEN_SEMICOLON, runConfig);

        return this;
    }

    @Override
    public void visit(InterpreterStackIdentifierContext context, RunConfiguration runConfig) {
        // TODO: fix this, assumes Int value
        InterpreterValueInt ivi = new InterpreterValueInt(Integer.valueOf(value));
        context.put(identifier, ivi);
    }
}
