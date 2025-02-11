public class Main {

    public static boolean BE_VERBOSE = false;
    public static boolean BE_VERY_VERBOSE = false;

    public static final int CODE_OK = 0;
    public static final int CODE_FAIL = 1;

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
            } else if (arg.equals("-v") || arg.equals("--verbose")) {
                Main.BE_VERBOSE = true;
            } else if (arg.equals("-vv") || arg.equals("--very-verbose")) {
                Main.BE_VERBOSE = true;
                Main.BE_VERY_VERBOSE = true;
            } else {
                lexerInputFile = arg;
            }
        }

        if (lex) {
            System.out.println("Running Lexer: " + lexerInputFile + " -> " + parserInputFile);
            try {
                Lexer.lexFile(lexerInputFile, parserInputFile);
            } catch (LexerException e) {
                System.out.println("Lexer Failure");
                System.exit(CODE_FAIL);
            }
        }
        if (parse) {
            System.out.println("Running Parser: "+parserInputFile+" -> "+ assemblerInputFile);
            Parser.parseFile(parserInputFile, assemblerInputFile);
        }
        if (assemble) {
            System.out.println("Running Assembler: "+assemblerInputFile+" -> "+ finalOutputFile);
            Assembler.assembleFile(assemblerInputFile, finalOutputFile);
        }

        System.exit(CODE_OK);
    }
}
