import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.tessera_lang.Main;
import org.tessera_lang.RunConfiguration;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FullTests {

    @Test
    void shouldFail() {
        assertThrows(Exception.class, () -> Main.run("", null));
    }

    @Test
    void fullTestALexer() {
        // All we are doing is checking that an error is not thrown
        RunConfiguration runConfig = new RunConfiguration();
        runConfig.setOut(System.out);

        runConfig.setBeVerbose(true);
        runConfig.setBeVeryVerbose(true);

        // VERY important, if this is false the tests WILL fail
        runConfig.setWeb(true);

        runConfig.setRunLexer(true);
        runConfig.setRunParser(false);
        runConfig.setRunInterpreter(false);

        String input = "4 * 2 + 5";

        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> Main.run(input, runConfig));
    }

    @Test
    void fullTestAParser() {
        // All we are doing is checking that an error is not thrown
        RunConfiguration runConfig = new RunConfiguration();
        runConfig.setOut(System.out);

        runConfig.setBeVerbose(true);
        runConfig.setBeVeryVerbose(true);

        // VERY important, if this is false the tests WILL fail
        runConfig.setWeb(true);

        runConfig.setRunLexer(true);
        runConfig.setRunParser(true);
        runConfig.setRunInterpreter(false);

        String input = "4 * 2 + 5";

        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> Main.run(input, runConfig));
    }

    @Test
    void fullTestAParser2() {
        // All we are doing is checking that an error is not thrown
        RunConfiguration runConfig = new RunConfiguration();
        runConfig.setOut(System.out);

        runConfig.setBeVerbose(true);
        runConfig.setBeVeryVerbose(true);

        // VERY important, if this is false the tests WILL fail
        runConfig.setWeb(true);

        runConfig.setRunLexer(true);
        runConfig.setRunParser(true);
        runConfig.setRunInterpreter(false);

        String input = "8 + 6 - 3";

        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> Main.run(input, runConfig));
    }

    @Test
    void fullTestAInterpreter() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                // All we are doing is checking that an error is not thrown
                RunConfiguration runConfig = new RunConfiguration();
                runConfig.setOut(System.out);

                runConfig.setBeVerbose(true);
                runConfig.setBeVeryVerbose(true);

                // VERY important, if this is false the tests WILL fail
                runConfig.setWeb(true);

                runConfig.setRunLexer(true);
                runConfig.setRunParser(true);
                runConfig.setRunInterpreter(true);

                String input = "4 * 2 + 5";

                Main.run(input, runConfig);
            }
        });
    }
}
