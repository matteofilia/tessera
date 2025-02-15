package org.tessera_lang;

import org.tessera_lang.interpreter.Interpreter;
import org.tessera_lang.lexer.Lexer;
import org.tessera_lang.lexer.LexerException;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.parser.Parser;
import org.tessera_lang.parser.ParserASTNode;
import org.tessera_lang.parser.ParserException;

import java.util.ArrayList;

public class Main {

    public static boolean BE_VERBOSE = false;
    public static boolean BE_VERY_VERBOSE = false;
    public static boolean WEB = false;

    public static final int CODE_OK = 0;
    public static final int CODE_FAIL = 1;

    public static void main(String[] args){
        boolean lex = true;
        boolean parse = true;

        String lexerInputFile = "code.ts";
        String parserInputFile = "tokens.t";
        String assemblerInputFile = "tree.ast";

        for (String arg : args) {
            if (arg.equals("--lex")) {
                parse = false;
            } else if (arg.equals("--parse")) {
                // Do everything
            } else if (arg.equals("-v") || arg.equals("--verbose")) {
                Main.BE_VERBOSE = true;
                System.out.println("Output mode: verbose");
            } else if (arg.equals("-vv") || arg.equals("--very-verbose")) {
                Main.BE_VERBOSE = true;
                Main.BE_VERY_VERBOSE = true;
                System.out.println("Output mode: very verbose");
            } else if (arg.equals("-w") || arg.equals(("--web"))) {
                Main.WEB = true;
                if (Main.BE_VERBOSE) System.out.println("Running in web mode");
            } else {
                lexerInputFile = arg;
            }
        }

        ArrayList<LexerToken> lexerList = new ArrayList<>();
        ParserASTNode head = null;

        if (lex) {
            System.out.println("Running org.tessera_lang.lexer.Lexer: " + lexerInputFile + " -> " + parserInputFile);
            try {
                lexerList = Lexer.lexFile(lexerInputFile, parserInputFile);
            } catch (LexerException e) {
                System.out.println("Lexer Failure");
                System.exit(CODE_FAIL);
            }
        }
        if (parse) {
            System.out.println("Running org.tessera_lang.parser.Parser: "+parserInputFile+" -> "+ assemblerInputFile);
            try {
                 head = Parser.parseInput(lexerList);

                if  (Main.BE_VERBOSE) {
                    Parser.printDebugOutput(head);
                }

                if (head == null && Main.BE_VERBOSE) {
                    System.out.println("Head is null!");
                }

                if (Main.BE_VERBOSE) {
                    Parser.printDebugTraversal(head);
                }
            } catch (ParserException e) {
                System.out.println("Parser Failure");
                System.exit(CODE_FAIL);
            }
        }

        // Run Interpreter
        Interpreter.run(head);

        System.exit(CODE_OK);
    }
}
