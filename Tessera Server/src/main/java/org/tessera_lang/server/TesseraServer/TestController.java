package org.tessera_lang.server.TesseraServer;

import org.springframework.web.bind.annotation.*;
import org.tessera_lang.Lexer;
import org.tessera_lang.LexerException;
import org.tessera_lang.ParserException;

import java.text.ParseException;

@RestController
public class TestController {

    @GetMapping("/hello_world")
    public String helloWorld() {
        return "Hello World!";
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping("/lex")
    public String lex(@RequestBody String input) {
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
