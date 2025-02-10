import assembler.Assembler;
import lexer.Lexer;
import parser.Parser;

public class Main {

    public static void main(String[] args){
        boolean lex = true;
        boolean parse = true;
        boolean assemble = true;

        String lexerInputFile = "code.ts";
        String parserInputFile = "tokens.t";
        String assemblerInputFile = "tree.ast";
        String finalOutputFile = "tess";

        for (String arg : args) {
            if (arg.equals("--lex")) {
                parse = false;
                assemble = false;
            } else if (arg.equals("--parse")) {
                assemble = false;
            } else {
                lexerInputFile = arg;
            }
        }

        if (lex) {
            System.out.println("Running Lexer: " + lexerInputFile + " -> " + parserInputFile);
            Lexer.lexFile(lexerInputFile, parserInputFile);
        }
        if (parse) {
            System.out.println("Running Parser: "+parserInputFile+" -> "+ assemblerInputFile);
            Parser.parseFile(parserInputFile, assemblerInputFile);
        }
        if (assemble) {
            System.out.println("Running Assembler: "+assemblerInputFile+" -> "+ finalOutputFile);
            Assembler.assembleFile(assemblerInputFile, finalOutputFile);
        }
    }
}
