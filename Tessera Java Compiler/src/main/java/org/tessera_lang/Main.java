package org.tessera_lang;

import org.tessera_lang.parser.Parser;

public class Main {

    public static boolean BE_VERBOSE = false;
    public static boolean BE_VERY_VERBOSE = false;
    public static boolean WEB = false;

    public static final int CODE_OK = 0;
    public static final int CODE_FAIL = 1;

    public static native void sendToHTML(String s);

    public String processInput(String input) {
        // TODO: hook this up
        return null;
    }

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
            } else if (arg.equals("-w") || arg.equals(("--web"))) {
                Main.WEB = true;
                if (Main.BE_VERBOSE) System.out.println("Running in web mode");
            } else {
                lexerInputFile = arg;
            }
        }

        if (lex) {
            System.out.println("Running org.tessera_lang.Lexer: " + lexerInputFile + " -> " + parserInputFile);
            try {
                Lexer.lexFile(lexerInputFile, parserInputFile);
            } catch (LexerException e) {
                System.out.println("org.tessera_lang.Lexer Failure");
                System.exit(CODE_FAIL);
            }
        }
        if (parse) {
            System.out.println("Running org.tessera_lang.parser.Parser: "+parserInputFile+" -> "+ assemblerInputFile);
            Parser.parseFile(parserInputFile, assemblerInputFile);
        }
        if (assemble) {
            System.out.println("Running org.tessera_lang.Assembler: "+assemblerInputFile+" -> "+ finalOutputFile);
            Assembler.assembleFile(assemblerInputFile, finalOutputFile);
        }

        System.exit(CODE_OK);
    }
}
