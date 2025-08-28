package org.tessera_lang.server.TesseraServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tessera_lang.Main;
import org.tessera_lang.interpreter.Interpreter;
import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.lexer.Lexer;
import org.tessera_lang.lexer.LexerException;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.parser.Parser;
import org.tessera_lang.parser.ParserASTNode;
import org.tessera_lang.parser.ParserException;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

@RestController
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @CrossOrigin(origins = {"${TESSERA_WEB_HOST}", "${TESSERA_WEB_HOST_B}"})
    @GetMapping(value="/hello_world", produces= MediaType.TEXT_PLAIN_VALUE)
    public String helloWorld() {
        return "Hello Tessera! \n" + Main.asciiArt;
    }

    @CrossOrigin(origins = {"${TESSERA_WEB_HOST}", "${TESSERA_WEB_HOST_B}"})
    @PostMapping("/run_interpreter")
    public String runInterpreter(@RequestBody String rawInput) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);

        ArrayList<LexerToken> lexerList = new ArrayList<>();
        ArrayList<ParserASTNode> trees = new ArrayList<>();
        try {
            try {
                lexerList = Lexer.lexText(rawInput);
            } catch (LexerException e) {
                return "Error: LexerException (lexing failure)";
            }

            try {
                trees = Parser.parse(lexerList);
            } catch (ParserException p) {
                // If parsing fails, print lexer output
                String fullOutput = "";
                fullOutput += "Error: ParserException (parser failure) \n";
                fullOutput += "Here is the lexing output: \n";
                fullOutput += Lexer.toText(lexerList);

                return fullOutput;
            }

            // Run Interpreter
            try {
                Interpreter.run(trees, out);
            } catch (InterpreterException e) {
                return e.getMessage();
            }

            return baos.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "ERROR";
        }
    }
}
