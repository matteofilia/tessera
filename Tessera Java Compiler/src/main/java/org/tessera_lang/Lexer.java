package org.tessera_lang;

import java.io.*;
import java.util.ArrayList;

public class Lexer {

    public static String toText(ArrayList<LexerToken> list) {
        StringBuilder output = new StringBuilder();

        for (LexerToken token : list) {
            output.append(token.getId());
            output.append(" ");
        }

        return output.toString();
    }

    public static ArrayList<LexerToken> lexText(String input) throws LexerException {
        ArrayList<LexerToken> list = new ArrayList<>();

        // TODO: fix this problem, please! Should be splitting on newlines
        String[] lines = input.split("\n");
        for (String line : lines) {
            line = line.trim();

            if (Main.BE_VERBOSE) System.out.println("Checking line: "+line);
            for (LexerToken token : LexerTokens.checkMatches(line)) {
                // Match Found
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
                    input.append(line);
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
