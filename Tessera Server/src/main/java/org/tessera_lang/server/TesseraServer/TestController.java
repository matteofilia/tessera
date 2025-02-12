package org.tessera_lang.server.TesseraServer;

import org.springframework.web.bind.annotation.*;
import org.tessera_lang.Lexer;
import org.tessera_lang.LexerException;

@RestController
public class TestController {

    @GetMapping("/hello_world")
    public String helloWorld() {
        return "Hello World!";
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping("/lex")
    public String lex(@RequestBody String input) {
        String output = ";";
        try {
            output = Lexer.lexText(input);
        } catch (LexerException e) {
            return "Error: LexerException";
        }
        return output;
    }
}
