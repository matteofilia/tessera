package org.tessera_lang.server.TesseraServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tessera_lang.Main;
import org.tessera_lang.RunConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RestController
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @CrossOrigin(origins = {"${TESSERA_WEB_HOST}", "${TESSERA_WEB_HOST_B}"})
    @GetMapping(value="/hello_world", produces= MediaType.TEXT_PLAIN_VALUE)
    public String helloWorld(@RequestParam(defaultValue = "user") String name) {
        return Main.asciiArt + "\n" + Main.generateThankYouMessage(name);
    }

    @CrossOrigin(origins = {"${TESSERA_WEB_HOST}", "${TESSERA_WEB_HOST_B}"})
    @PostMapping("/run")
    public String run(
            @RequestBody String rawInput,
            @RequestParam(defaultValue = "true") boolean runLexer,
            @RequestParam(defaultValue = "true") boolean runParser,
            @RequestParam(defaultValue = "true") boolean runInterpreter,
            @RequestParam(defaultValue = "true") boolean beVerbose,
            @RequestParam(defaultValue = "false") boolean beVeryVerbose
    ) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);

        RunConfiguration runConfig = new RunConfiguration();

        runConfig.setRunLexer(runLexer);
        runConfig.setRunParser(runParser);
        runConfig.setRunInterpreter(runInterpreter);

        runConfig.setBeVerbose(beVerbose);
        runConfig.setBeVeryVerbose(beVeryVerbose);

        Main.run(out, runConfig, rawInput);
        return baos.toString();
    }
}
