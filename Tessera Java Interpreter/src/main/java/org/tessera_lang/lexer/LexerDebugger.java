package org.tessera_lang.lexer;

import org.tessera_lang.RunConfiguration;

import java.util.ArrayList;

public class LexerDebugger {

    public static String simpleTokenDebugString(LexerToken token) {
        return token.getIdentifier().getName();
    }

    public static String tokenDebugString(LexerToken token) {
        StringBuilder output = new StringBuilder();
        if (token.hasValue()) {
            output.append("LexerToken " + token.getIdentifier().getName() + " (" + token.getValue() + ")\n");
        } else {
            output.append("LexerToken " + token.getIdentifier().getName() + "\n");
        }
        output.append(" - Origin " + "R" + String.valueOf(token.getOriginRow()) + ":" + "C" + String.valueOf(token.getOriginColumn() + "\n"));
        output.append(" - Origin Line: " + token.getOriginLine() + "\n");

        return output.toString();
    }

    public static void debugLexerTokenList(ArrayList<LexerToken> list, RunConfiguration runConfig) {
        if (runConfig.shouldBeVerbose() || runConfig.shouldDisplayLexerOnly()) {
            if (!runConfig.shouldDisplayLexerOnly()) runConfig.getOut().print("\n");

            for (LexerToken token : list) {
                if (runConfig.shouldBeVerbose() || runConfig.shouldBeVeryVerbose()) {
                    runConfig.getOut().print(tokenDebugString(token));
                } else {
                    runConfig.getOut().print(simpleTokenDebugString(token));
                    runConfig.getOut().print(" ");
                }
            }
            if (!runConfig.shouldDisplayLexerOnly()) runConfig.getOut().print("\n");
        }
    }
}
