package org.tessera_lang.parser;

import org.tessera_lang.LexerToken;
import org.tessera_lang.LexerTokens;

import java.util.ArrayList;

public class Parser {

    public static ASTNode parse(ArrayList<LexerToken> list) {
        StringBuilder output = new StringBuilder();

        for (LexerToken token : list) {
            if (token.getId() == LexerTokens.TOKEN_CONSTANT) {

            }
        }

        return output.toString();
    }

    public static void parseFile(String inputFile, String outputFile) {
        // TODO
    }
}
