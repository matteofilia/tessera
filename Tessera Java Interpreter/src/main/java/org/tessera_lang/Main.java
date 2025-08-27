package org.tessera_lang;

import org.tessera_lang.interpreter.Interpreter;
import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.lexer.Lexer;
import org.tessera_lang.lexer.LexerDebugger;
import org.tessera_lang.lexer.LexerException;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.parser.Parser;
import org.tessera_lang.parser.ParserASTNode;
import org.tessera_lang.parser.ParserDebugger;
import org.tessera_lang.parser.ParserException;

import java.util.ArrayList;

public class Main {

    public static boolean BE_VERBOSE = false;
    public static boolean BE_VERY_VERBOSE = false;
    public static boolean WEB = false;

    public static final int CODE_OK = 0;
    public static final int CODE_FAIL = 1;

    private static void printCoolAsciiThing() {
        System.out.println("\n" +
                "\n" +
                " _____                            \n" +
                "|_   _|__  ___ ___  ___ _ __ __ _ \n" +
                "  | |/ _ \\/ __/ __|/ _ \\ '__/ _` |\n" +
                "  | |  __/\\__ \\__ \\  __/ | | (_| |\n" +
                "  |_|\\___||___/___/\\___|_|  \\__,_|\n\n" +
                "  * Thank you for using Tessera! * \n"
        );
    }

    private static void printHelp() {
        System.out.println("*** Tessera Interpreter Help ***");
        System.out.println("");
        System.out.println("Arguments: ");
        System.out.println("--lex --- Run Lexer ONLY");
        System.out.println("--parse --- Run Lexer AND Parser");
        System.out.println("--interpreter --- Run Lexer AND Parser AND Interpreter");
        System.out.println("--web --- Run In Web Mode");
        System.out.println("");
        System.out.println("-h --help --- Display Help");
        System.out.println("-v --verbose --- Display Output Verbosely");
        System.out.println("-vv --very-verbose --- Display Output Very Verbosely");

        System.out.println("");
        System.out.println("About: ");
        System.out.println("Tessera is an interpreted language, with the interpreter being written in Java.");

        System.out.println("");
        System.out.println("Stages: ");
        System.out.println("1 - Run Lexer");
        System.out.println("2 - Run Parser");
        System.out.println("3 - Run Interpreter");

        System.out.println("Files: ");
        System.out.println("Default Lexer File: code.tess");
        System.out.println("Default Parser File: tokens.t");
        System.out.println("Default Interpreter File: N/A");

        System.out.println("");
        System.out.println("Examples: ");
        System.out.println("./tessera file.tess");
        System.out.println("./tessera --lex --verbose file2.tess");
    }

    // TODO: this entire thing is terrible... fix please

    public static void main(String[] args){

        printCoolAsciiThing();
        System.out.println("For help, please use -h or --help");

        boolean runLexer = true;
        boolean runParser = true;
        boolean runInterpreter = true;

        String lexerInputFile = "code.tess";
        String parserInputFile = "tokens.t";

        for (String arg : args) {
            if (arg.equals("--lex")) {
                runParser = false;
                runInterpreter = false;
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
            } else if (arg.equals("-h") || arg.equals("--help")) {
                printHelp();
            } else {
                String file = arg;
                System.out.println("Using file: "+file);
                lexerInputFile = file;
            }
        }

        ArrayList<LexerToken> lexerList = new ArrayList<>();
        ParserASTNode head = null;

        if (runLexer) {
            System.out.println("Running org.tessera_lang.lexer.Lexer: " + lexerInputFile + " -> " + parserInputFile);
            try {
                lexerList = Lexer.lexFile(lexerInputFile, parserInputFile);

                if (Main.BE_VERBOSE) {
                    System.out.print("Lexer Output: ");
                    LexerDebugger.debugLexerTokenList(lexerList);
                }
            } catch (LexerException e) {
                System.out.println("Lexer Failure");
                System.exit(CODE_FAIL);
            }
        }
        if (runParser) {
            System.out.println("Running org.tessera_lang.parser.Parser: "+parserInputFile);
            try {
                 head = Parser.parseInput(lexerList);

                if  (Main.BE_VERBOSE) {
                    ParserDebugger.printDebugOutput(head);
                }

                if (head == null && Main.BE_VERBOSE) {
                    System.out.println("Head is null!");
                }

                if (Main.BE_VERBOSE) {
                    ParserDebugger.printDebugTraversal(Parser.getHeadRecursive(head));
                }
            } catch (ParserException e) {
                System.out.println("Parser Failure");
                System.exit(CODE_FAIL);
            }
        }

        // Run Interpreter
        if (runInterpreter) {
            try {
                Interpreter.run(head);
            } catch (InterpreterException e) {
                System.out.println(e.getMessage());
            }
        }

        System.exit(CODE_OK);
    }
}
