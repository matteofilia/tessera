package org.tessera_lang.lexer;

import org.tessera_lang.Main;

import java.util.ArrayList;

public class LexerDebugger {

    public static void debugLexerTokenList(ArrayList<LexerToken> list) {
        if (Main.BE_VERBOSE) {
            System.out.print("\n");
            for (LexerToken token : list) {
                if (token.hasValue()) {
                    System.out.print(token.getIdentifier().getName() + " (" + token.getValue() + ") ");
                } else {
                    System.out.print(token.getIdentifier().getName() + " ");
                }
            }
            System.out.print("\n");
        }
    }
}
