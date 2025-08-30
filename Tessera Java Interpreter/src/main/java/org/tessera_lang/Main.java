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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static boolean BE_VERBOSE = false;
    public static boolean BE_VERY_VERBOSE = false;
    public static boolean WEB = false;

    public static final int CODE_OK = 0;
    public static final int CODE_FAIL = 1;

    public static final String asciiArt = "\n" +
            "\n" +
            " _____                            \n" +
            "|_   _|__  ___ ___  ___ _ __ __ _ \n" +
            "  | |/ _ \\/ __/ __|/ _ \\ '__/ _` |\n" +
            "  | |  __/\\__ \\__ \\  __/ | | (_| |\n" +
            "  |_|\\___||___/___/\\___|_|  \\__,_|\n\n" +
            "  * Thank you for using Tessera! * \n";

    private static void printCoolAsciiThing() {
        System.out.println(asciiArt);
    }

    private static void printHelp() {
        System.out.println("");
        System.out.println("*** TESSERA INTERPRETER HELP ***");
        System.out.println("");
        System.out.println("Arguments: ");
        System.out.println("  -l --lex --- Run Lexer ONLY");
        System.out.println("  -p --parse --- Run Lexer AND Parser");
        System.out.println("  -i --interpreter --- Run Lexer AND Parser AND Interpreter");
        System.out.println("  -w --web --- Run In Web Mode");
        System.out.println("  -c --code --- Print Out Code (For Reference)");
        System.out.println("");
        System.out.println("  -h --help --- Display Help");
        System.out.println("  -v --verbose --- Display Output Verbosely");
        System.out.println("  -vv --very-verbose --- Display Output Very Verbosely");

        System.out.println("");
        System.out.println("About: ");
        System.out.println("  Tessera is an interpreted language, with the interpreter being written in Java.");

        System.out.println("");
        System.out.println("Stages: ");
        System.out.println("  1 - Run Lexer");
        System.out.println("  2 - Run Parser");
        System.out.println("  3 - Run Interpreter");

        System.out.println("");
        System.out.println("Files: ");
        System.out.println("  Default Lexer File: code.tess");
        System.out.println("  Default Parser File: tokens.t");
        System.out.println("  Default Interpreter File: N/A");

        System.out.println("");
        System.out.println("Examples: ");
        System.out.println("  ./tessera file.tess");
        System.out.println("  ./tessera --lex --verbose file2.tess");
        System.out.println("");
    }

    // TODO: this entire thing is terrible... fix please

    public static void main(String[] args){

        boolean runLexer = false;
        boolean runParser = false;
        boolean runInterpreter = false;

        boolean printCode = false;

        String lexerInputFile = "code.tess";
        String parserInputFile = "tokens.t";

        printCoolAsciiThing();
        System.out.println("For help, please use -h or --help");

        for (String arg : args) {
            if (arg.equals("-l") || arg.equals("--lex")) {
                // Run Lexer ONLY
                runLexer = true;
            } else if (arg.equals("-p") || arg.equals("--parse")) {
                // Run Lexer AND Parser
                runLexer = true;
                runParser = true;
            } else if (arg.equals("-i") || arg.equals("--interpreter")) {
                // Run Lexer AND Parser AND Interpreter
                runLexer = true;
                runParser = true;
                runInterpreter = true;
            } else if (arg.equals("-c") || arg.equals("--code")) {
                printCode = true;
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

        if (!runLexer && !runParser && !runInterpreter) {
            System.out.println("No Action Specified");
            System.out.println("Running Lexer AND Parser AND Interpreter");

            runLexer = true;
            runParser = true;
            runInterpreter = true;
        }

        ArrayList<LexerToken> lexerList = new ArrayList<>();
        ArrayList<ParserASTNode> trees = new ArrayList<>();

        if (printCode) {
            StringBuilder input = new StringBuilder();
            System.out.println("");
            System.out.println("CODE " + "(" + lexerInputFile + ")");

            try {
                FileReader fileReader = new FileReader(lexerInputFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                try {
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        input.append(line + "\n");
                        line = bufferedReader.readLine();
                    }

                    System.out.println(input.toString());

                } catch (IOException e) {
                    System.out.println("Error: IO Exception");
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        if (runLexer) {
            System.out.println("");
            System.out.println("RUNNING LEXER");
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
            System.out.println("");
            System.out.println("RUNNING PARSER");
            System.out.println("Running org.tessera_lang.parser.Parser: "+parserInputFile);
            try {
                 trees = Parser.parse(lexerList);

                for (ParserASTNode head : trees) {
                    if  (Main.BE_VERBOSE) {
                        ParserDebugger.printDebugOutput(head);
                    }

                    if (head == null && Main.BE_VERBOSE) {
                        System.out.println("Head is null!");
                    }

                    if (Main.BE_VERBOSE) {
                        ParserDebugger.printDebugTraversal(Parser.getHeadRecursive(head));
                    }
                }
            } catch (ParserException e) {
                System.out.println("Parser Failure");
                System.exit(CODE_FAIL);
            }
        }

        // Run Interpreter
        if (runInterpreter) {
            System.out.println("");
            System.out.println("RUNNING INTERPRETER");

            try {
                Interpreter.run(trees, System.out);
            } catch (InterpreterException e) {
                System.out.println(e.getMessage());
            }
        }

        System.exit(CODE_OK);
    }
}
