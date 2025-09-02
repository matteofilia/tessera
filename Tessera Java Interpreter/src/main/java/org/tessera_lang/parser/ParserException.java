package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.lexer.LexerDebugger;
import org.tessera_lang.lexer.LexerToken;

public class ParserException extends Exception {

    private LexerToken originToken;

    public ParserException() {
        super("Parser Exception");
    }

    public ParserException(String message, LexerToken originToken) {
        super(message);
        this.originToken = originToken;
    }

    public LexerToken getOriginToken() {
        return originToken;
    }

    public void print(RunConfiguration runConfig) {
        runConfig.getOut().println(getMessage());
        runConfig.getOut().println("Error Origin: ");
        runConfig.getOut().print(LexerDebugger.tokenDebugString(originToken));
    }
}
