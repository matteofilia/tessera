package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Lexer {

    // While input isn't empty:
    //  if input starts with whitespace:
    //    trim whitespace from start of input
    // else:
    //  find the longest match at start of input for any regex pattern
    //  if no match is found, raise an error
    //  convert matching substring into a token
    //  remove matching substring from start of input
    // TODO: fix name
    public static void doSomething(String filename) {
        try {
            FileReader fileReader = new FileReader(new File(filename));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }
    }
}
