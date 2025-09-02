package org.tessera_lang.lexer;

import org.tessera_lang.RunConfiguration;

import java.util.ArrayList;

public class LexerDebugger {

    public static String tokenDebugString(LexerToken token) {
        StringBuilder output = new StringBuilder();
        if (token.hasValue()) {
            output.append("LexerToken " + token.getIdentifier().getName() + " (" + token.getValue() + ")\n");
        } else {
            output.append("LexerToken " + token.getIdentifier().getName() + "\n");
        }
        output.append(" - Origin "+"R"+String.valueOf(token.getOriginRow()) + ":" + "C" + String.valueOf(token.getOriginColumn()+"\n"));
        output.append(" - Origin Line: "+ token.getOriginLine()+"\n");

        return output.toString();
    }

    public static void debugLexerTokenList(ArrayList<LexerToken> list, RunConfiguration runConfig) {
        if (runConfig.shouldBeVerbose()) {
            runConfig.getOut().print("\n");
            for (LexerToken token : list) {
                runConfig.getOut().print(tokenDebugString(token));
            }
            runConfig.getOut().print("\n");
        }
    }
}
