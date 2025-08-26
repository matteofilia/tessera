package org.tessera_lang.server.TesseraServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.tessera_lang.interpreter.Interpreter;
import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.lexer.Lexer;
import org.tessera_lang.lexer.LexerException;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.parser.Parser;
import org.tessera_lang.parser.ParserASTNode;
import org.tessera_lang.parser.ParserException;

import java.awt.*;
import java.util.ArrayList;

@RestController
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Value("${TESSERA_WEB_HOST}")
    private String TESSERA_WEB_HOST = "Please Set Me";

    @CrossOrigin(origins = {"${TESSERA_WEB_HOST}"})
    @GetMapping("/hello_world")
    public String helloWorld() {
        return "Hello World!";
    }

    @CrossOrigin(origins = {"${TESSERA_WEB_HOST}"})
    @PostMapping("/run_interpreter")
    public String runInterpreter(@RequestBody String rawInput) {
        logger.info("TESSERA_WEB_HOST="+TESSERA_WEB_HOST);

        ArrayList<LexerToken> lexerList = new ArrayList<>();
        ParserASTNode head = null;
        try {
            try {
                lexerList = Lexer.lexText(rawInput);
            } catch (LexerException e) {
                return "Error: LexerException (lexing failure)";
            }

            try {
                head = Parser.parse(lexerList);
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
                Interpreter.run(head);

                if (head == null) {
                    return "NO VALUE";
                }

                if (head.hasValue()) {
                    return head.getValue().toString();
                } else {
                    return "NO VALUE";
                }
            } catch (InterpreterException e) {
                return e.getMessage();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "ERROR";
        }
    }
}
