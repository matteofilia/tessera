package org.tessera_lang.lexer;

import org.tessera_lang.Main;

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

    public static ArrayList<LexerToken> lexText(String input) throws LexerException {
        ArrayList<LexerToken> list = new ArrayList<>();

        String[] lines = input.split("\\r?\\n");

        for (int indexRow = 0; indexRow < lines.length; indexRow++) {
            String line = lines[indexRow];
            line = line.trim();

            if (Main.BE_VERBOSE) System.out.println("Checking line: "+line);
            for (LexerToken token : LexerTokens.checkMatches(line)) {
                // Set origin row
                token.setOriginRow(indexRow);

                // Match found, add to list
                list.add(token);
            }
        }

        return list;
    }

    public static ArrayList<LexerToken> lexFile(String inputFile, String outputFile) throws LexerException {
        StringBuilder input = new StringBuilder();
        String output = "";

        try {
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    input.append(line + "\n");
                    line = bufferedReader.readLine();
                }
            } catch (IOException e){
                System.out.println("Error: IO Exception");
            }

            return lexText(input.toString());

        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }

        try {
            Writer writer = new FileWriter(outputFile);
            writer.write(output);
            writer.close();;
        } catch (IOException e) {
            System.out.println("Error: IO Exception");
        }

        return null;
    }
}
