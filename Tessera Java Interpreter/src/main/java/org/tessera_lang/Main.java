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

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static final int CODE_OK = 0;
    public static final int CODE_FAIL = 1;

    public static final String asciiArt = "\n" +
            "\n" +
            " _____                            \n" +
            "|_   _|__  ___ ___  ___ _ __ __ _ \n" +
            "  | |/ _ \\/ __/ __|/ _ \\ '__/ _` |\n" +
            "  | |  __/\\__ \\__ \\  __/ | | (_| |\n" +
            "  |_|\\___||___/___/\\___|_|  \\__,_|\n";

    public static final String generateThankYouMessage(String name) {
        return "Thank you for programming with Tessera, "+name;
    }

    private static void printCoolAsciiThing(PrintStream out) {
        String name = "user";

        out.println(asciiArt);
        out.println(generateThankYouMessage(name));
    }

    private static void printHelp(PrintStream out) {
        out.println("");
        out.println("*** TESSERA INTERPRETER HELP ***");
        out.println("");
        out.println("Arguments: ");
        out.println("  -l --lex --- Run Lexer ONLY");
        out.println("  -p --parse --- Run Lexer AND Parser");
        out.println("  -i --interpreter --- Run Lexer AND Parser AND Interpreter");
        out.println("  -w --web --- Run In Web Mode");
        out.println("  -c --code --- Print Out Code (For Reference)");
        out.println("");
        out.println("  -h --help --- Display Help");
        out.println("  -v --verbose --- Display Output Verbosely");
        out.println("  -vv --very-verbose --- Display Output Very Verbosely");

        out.println("");
        out.println("About: ");
        out.println("  Tessera is an interpreted language, with the interpreter being written in Java.");

        out.println("");
        out.println("Stages: ");
        out.println("  1 - Run Lexer");
        out.println("  2 - Run Parser");
        out.println("  3 - Run Interpreter");

        out.println("");
        out.println("Files: ");
        out.println("  Default Lexer File: code.tess");
        out.println("  Default Parser File: tokens.t");
        out.println("  Default Interpreter File: N/A");

        out.println("");
        out.println("Examples: ");
        out.println("  ./tessera file.tess");
        out.println("  ./tessera --lex --verbose file2.tess");
        out.println("");
    }

    public static void run(PrintStream out, RunConfiguration runConfig, String input) {

        if (runConfig.shouldBeVerbose()) {
            printCoolAsciiThing(out);
        }

        if (!runConfig.isWeb()) {
            out.println("For help, please use -h or --help");
        }

        if (runConfig.shouldBeVerbose()) {
            out.println("Output mode: verbose");

            if (runConfig.shouldBeVeryVerbose()) {
                out.println("Output mode: very verbose");
            }
        }

        if (!runConfig.shouldRunLexer() && !runConfig.shouldRunParser() && !runConfig.shouldRunInterpreter()) {
            if (runConfig.shouldBeVerbose()) {
                out.println("No Action Specified");
                out.println("Running Lexer AND Parser AND Interpreter");
            }

            runConfig.setRunLexer(true);
            runConfig.setRunParser(true);
            runConfig.setRunInterpreter(true);
        }

        ArrayList<LexerToken> lexerList = new ArrayList<>();
        ArrayList<ParserASTNode> trees = new ArrayList<>();

        if (runConfig.shouldPrintCode()) {

        }

        if (runConfig.shouldRunLexer()) {
            if (runConfig.shouldBeVerbose()) {
                out.println("");
                out.println("RUNNING LEXER");
            }

            try {
                lexerList = Lexer.lexText(input, runConfig);

                if (runConfig.shouldBeVerbose()) {
                    out.print("Lexer Output: ");
                    LexerDebugger.debugLexerTokenList(lexerList);
                }
            } catch (LexerException e) {
                out.println("Lexer Failure");
                System.exit(CODE_FAIL);
            }
        }
        if (runConfig.shouldRunParser()) {
            if (runConfig.shouldBeVerbose()) {
                out.println("");
                out.println("RUNNING PARSER");
            }

            try {
                trees = Parser.parse(lexerList, runConfig);

                for (ParserASTNode head : trees) {
                    if  (runConfig.shouldBeVerbose()) {
                        ParserDebugger.printDebugOutput(head);
                    }

                    if (head == null && runConfig.shouldBeVerbose()) {
                        out.println("Head is null!");
                    }

                    if (runConfig.shouldBeVeryVerbose()) {
                        ParserDebugger.printDebugTraversal(Parser.getHeadRecursive(head));
                    }
                }
            } catch (ParserException e) {
                out.println("Parser Failure");
                System.exit(CODE_FAIL);
            }
        }

        // Run Interpreter
        if (runConfig.shouldRunInterpreter()) {
            if (runConfig.shouldBeVerbose()) {
                out.println("");
                out.println("RUNNING INTERPRETER");
            }

            try {
                Interpreter.run(trees, System.out);
            } catch (InterpreterException e) {
                out.println(e.getMessage());
                System.exit(CODE_FAIL);
            }
        }

        System.exit(CODE_OK);
    }

    public static void main(String[] args){
        RunConfiguration runConfig = new RunConfiguration();
        PrintStream out = System.out;
        StringBuilder input = new StringBuilder();
        String file = null;

        // Parse Args
        for (String arg : args) {
            if (arg.equals("-l") || arg.equals("--lex")) {
                // Run Lexer ONLY
                runConfig.setRunLexer(true);
            } else if (arg.equals("-p") || arg.equals("--parse")) {
                // Run Lexer AND Parser
                runConfig.setRunLexer(true);
                runConfig.setRunParser(true);
            } else if (arg.equals("-i") || arg.equals("--interpreter")) {
                // Run Lexer AND Parser AND Interpreter
                runConfig.setRunLexer(true);
                runConfig.setRunParser(true);
                runConfig.setRunInterpreter(true);
            } else if (arg.equals("-c") || arg.equals("--code")) {
                runConfig.setPrintCode(true);
            } else if (arg.equals("-v") || arg.equals("--verbose")) {
                runConfig.setBeVerbose(true);
            } else if (arg.equals("-vv") || arg.equals("--very-verbose")) {
                runConfig.setBeVerbose(true);
                runConfig.setBeVeryVerbose(true);
            } else if (arg.equals("-w") || arg.equals(("--web"))) {
                runConfig.setWeb(true);

            } else if (arg.equals("-h") || arg.equals("--help")) {
                printHelp(System.out);
            } else {
                file = arg;

                if (runConfig.shouldBeVerbose()) {
                    out.println("Using file: " + file);
                }
            }
        }

        // Load File
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    input.append(line + "\n");
                    line = bufferedReader.readLine();
                }

                out.println(input.toString());

            } catch (IOException e) {
                if (runConfig.shouldBeVerbose()) {
                    out.println("Error: IO Exception");
                }
                System.exit(CODE_FAIL);
            }
        } catch (FileNotFoundException e) {
            if (runConfig.shouldBeVerbose()) {
                out.println(e.getMessage());
            }
            System.exit(CODE_FAIL);
        }

        run(out, runConfig, input.toString());
    }
}
