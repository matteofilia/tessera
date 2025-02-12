package org.tessera_lang;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Lexer {

    public static String lexText(String input) throws LexerException {
        StringBuilder output = new StringBuilder();

        // TODO: fix this problem, please! Should be splitting on newlines
        String[] lines = input.split("\n");
        for (String line : lines) {
            line = line.trim();

            if (Main.BE_VERBOSE) System.out.println("Checking line: "+line);
            for (LexerToken token : LexerTokens.checkMatches(line)) {
                // Match Found
                output.append(token.getId());
                output.append(" ");
            }
        }

        return output.toString();
    }

    public static void lexFile(String inputFile, String outputFile) throws LexerException {
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

            output = lexText(input.toString());

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

    }
}
