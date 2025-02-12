package org.tessera_lang.server.TesseraServer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello_world")
    public String helloWorld() {
        return "Hello World!";
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping("/lex")
    public String lex() {
        return "(TODO: Lexer Output Goes Here)";
    }
}
