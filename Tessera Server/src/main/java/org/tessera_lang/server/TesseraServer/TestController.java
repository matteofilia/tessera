package org.tessera_lang.server.TesseraServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello_world")
    public String helloWorld() {
        return "Hello World!";
    }
}
