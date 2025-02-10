package lexer;

import java.io.*;

public class Lexer {

    // While input isn't empty:
    //  if input starts with whitespace:
    //    trim whitespace from start of input
    // else:
    //  find the longest match at start of input for any regex pattern
    //  if no match is found, raise an error
    //  convert matching substring into a token
    //  remove matching substring from start of input
    public static void lexFile(String inputFile, String outputFile) {
        try {
            FileReader fileReader = new FileReader(new File(inputFile));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                String line = bufferedReader.readLine();
            } catch (IOException e){
                System.out.println("Error: IO Exception");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }

        try {
            Writer writer = new FileWriter(outputFile);
            writer.write("Hello World");
            writer.close();;
        } catch (IOException e) {
            System.out.println("Error: IO Exception");
        }

    }
}
