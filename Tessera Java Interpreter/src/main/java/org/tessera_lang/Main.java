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

    private static void printCoolAsciiThing(RunConfiguration runConfig) {
        String name = "user";
        PrintStream out = runConfig.getOut();

        out.println(asciiArt);
        out.println(generateThankYouMessage(name));
    }

    private static void printHelp(RunConfiguration runConfig) {
        PrintStream out = runConfig.getOut();

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

    public static void run(String input, RunConfiguration runConfig) {

        if (runConfig.shouldBeVerbose()) {
            printCoolAsciiThing(runConfig);
        }

        if (!runConfig.isWeb() && runConfig.shouldBeVerbose()) {
            runConfig.getOut().println("For help, please use -h or --help");
        }

        if (runConfig.shouldBeVerbose()) {
            runConfig.getOut().println("Output mode: verbose");

            if (runConfig.shouldBeVeryVerbose()) {
                runConfig.getOut().println("Output mode: very verbose");
            }
        }

        if (!runConfig.shouldRunLexer() && !runConfig.shouldRunParser() && !runConfig.shouldRunInterpreter()) {
            if (runConfig.shouldBeVerbose()) {
                runConfig.getOut().println("No Action Specified");
                runConfig.getOut().println("Running Lexer AND Parser AND Interpreter");
            }

            runConfig.setRunLexer(true);
            runConfig.setRunParser(true);
            runConfig.setRunInterpreter(true);
        }

        ArrayList<LexerToken> lexerList = new ArrayList<>();
        ArrayList<ParserASTNode> trees = new ArrayList<>();

        if (runConfig.shouldPrintCode()) {
            runConfig.getOut().println(input.toString());
        }

        if (runConfig.shouldRunLexer()) {
            if (runConfig.shouldBeVerbose()) {
                runConfig.getOut().println("");
                runConfig.getOut().println("RUNNING LEXER");
            }

            try {
                lexerList = Lexer.lexText(input, runConfig);

                if (runConfig.shouldBeVerbose()) {
                    runConfig.getOut().print("Lexer Output: ");
                    LexerDebugger.debugLexerTokenList(lexerList, runConfig);
                }
            } catch (LexerException e) {
                runConfig.getOut().println("Lexer Failure");
                if (!runConfig.isWeb()) System.exit(CODE_FAIL);
            }
        }
        if (runConfig.shouldRunParser()) {
            if (runConfig.shouldBeVerbose()) {
                runConfig.getOut().println("");
                runConfig.getOut().println("RUNNING PARSER");
            }

            try {
                trees = Parser.parse(lexerList, runConfig);

                for (ParserASTNode head : trees) {
                    if  (runConfig.shouldBeVerbose()) {
                        ParserDebugger.printDebugOutput(head, runConfig);
                    }

                    if (head == null && runConfig.shouldBeVerbose()) {
                        runConfig.getOut().println("Head is null!");
                    }

                    if (runConfig.shouldBeVeryVerbose()) {
                        ParserDebugger.printDebugTraversal(Parser.getHeadRecursive(head), runConfig);
                    }
                }
            } catch (ParserException e) {
                runConfig.getOut().println("Parser Failure");
                if (!runConfig.isWeb()) System.exit(CODE_FAIL);
            }
        }

        // Run Interpreter
        if (runConfig.shouldRunInterpreter()) {
            if (runConfig.shouldBeVerbose()) {
                runConfig.getOut().println("");
                runConfig.getOut().println("RUNNING INTERPRETER");
            }

            try {
                Interpreter.run(trees, runConfig);
            } catch (InterpreterException e) {
                runConfig.getOut().println(e.getMessage());
                if (!runConfig.isWeb()) System.exit(CODE_FAIL);
            }
        }

        if (!runConfig.isWeb()) System.exit(CODE_OK);
    }

    public static void main(String[] args){
        RunConfiguration runConfig = new RunConfiguration();
        runConfig.setOut(System.out);

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
                printHelp(runConfig);
            } else {
                file = arg;

                if (runConfig.shouldBeVerbose()) {
                    runConfig.getOut().println("Using file: " + file);
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
            } catch (IOException e) {
                if (runConfig.shouldBeVerbose()) {
                    runConfig.getOut().println("Error: IO Exception");
                }
                if (!runConfig.isWeb()) System.exit(CODE_FAIL);
            }
        } catch (FileNotFoundException e) {
            if (runConfig.shouldBeVerbose()) {
                runConfig.getOut().println(e.getMessage());
            }
            if (!runConfig.isWeb()) System.exit(CODE_FAIL);
        }

        run(input.toString(), runConfig);
    }
}
