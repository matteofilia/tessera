package org.tessera_lang;

import java.io.*;

public class Lexer {

    public static void lexFile(String inputFile, String outputFile) throws LexerException {
        StringBuilder output = new StringBuilder();

        try {
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    line = line.trim();

                    for (LexerToken token : LexerTokens.checkMatches(line)) {
                        // Match Found
                        output.append(token.getId());
                        output.append(" ");
                    }

                    line = bufferedReader.readLine();
                }

                bufferedReader.close();
            } catch (IOException e){
                System.out.println("Error: IO Exception");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }

        try {
            Writer writer = new FileWriter(outputFile);
            writer.write(output.toString());
            writer.close();;
        } catch (IOException e) {
            System.out.println("Error: IO Exception");
        }

    }
}
