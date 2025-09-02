package org.tessera_lang.lexer;

import org.tessera_lang.Main;
import org.tessera_lang.RunConfiguration;

import java.io.*;
import java.util.ArrayList;

public class Lexer {

    public static String toText(ArrayList<LexerToken> list) {
        StringBuilder output = new StringBuilder();

        for (LexerToken token : list) {
            output.append(token.getIdentifier().getName());
            output.append(" ");
        }

        return output.toString();
    }

    public static ArrayList<LexerToken> lexText(String input, RunConfiguration runConfig) throws LexerException {
        ArrayList<LexerToken> list = new ArrayList<>();

        String[] lines = input.split("\\r?\\n");

        for (int indexRow = 0; indexRow < lines.length; indexRow++) {
            String line = lines[indexRow];
            line = line.trim();

            if (runConfig.shouldBeVerbose()) runConfig.getOut().println("Checking line: "+line);
            for (LexerToken token : LexerTokens.checkMatches(line, runConfig)) {
                // Set origin row
                token.setOriginRow(indexRow);

                // Match found, add to list
                list.add(token);
            }
        }

        return list;
    }
}
