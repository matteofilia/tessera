package org.tessera_lang.lexer;

import org.tessera_lang.Main;

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

    public static void debugLexerTokenList(ArrayList<LexerToken> list) {
        if (Main.BE_VERBOSE) {
            System.out.print("\n");
            for (LexerToken token : list) {
                System.out.print(tokenDebugString(token));
            }
            System.out.print("\n");
        }
    }
}
