package org.tessera_lang.server.TesseraServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.tessera_lang.lexer.Lexer;
import org.tessera_lang.lexer.LexerException;
import org.tessera_lang.parser.ParserException;

@RestController
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Value("${TESSERA_WEB_HOST}")
    private String TESSERA_WEB_HOST = "Please Set Me";

    @GetMapping("/hello_world")
    public String helloWorld() {
        return "Hello World!";
    }

    @CrossOrigin(origins = {"${TESSERA_WEB_HOST}"})
    @PostMapping("/lex")
    public String lex(@RequestBody String input) {
        logger.info("TESSERA_WEB_HOST="+TESSERA_WEB_HOST);

        String output = "";
        try {
            output = Lexer.toText(Lexer.lexText(input));
        } catch (LexerException e) {
            return "Error: LexerException (lexing failure)";
        }

        try {
            // TODO: add parser code here
            throw new ParserException();
        } catch (ParserException p) {
            // If parsing fails, print lexer output
            String fullOutput = "";
            fullOutput += "Error: ParserException (parser failure) \n";
            fullOutput += "Here is the lexing output: \n";
            fullOutput += output;

            return fullOutput;
        }
    }
}
