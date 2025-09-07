package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.interpreter.InterpreterStackIdentifierContext;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

import java.util.ArrayList;

public class ParserASTNodePrint extends ParserASTNodePlaceholder {

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list, RunConfiguration runConfig) throws ParserException {
        Parser.expect(list, LexerTokenIdentifier.TOKEN_PRINT, runConfig);
        Parser.twinPassAddNodes(list, this);
        Parser.weakExpect(list, LexerTokenIdentifier.TOKEN_SEMICOLON, runConfig);

        return this;
    }

    @Override
    public void visit(InterpreterStackIdentifierContext context, RunConfiguration runConfig) {
        // TODO: this code is still terrible...
        try {
            runConfig.getOut().println(getValue(context));
        } catch (InterpreterException e) {
            runConfig.getOut().println("Generic Interpreter Error");
        }
    }
}
