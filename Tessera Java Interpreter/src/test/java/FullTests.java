import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.tessera_lang.Main;
import org.tessera_lang.RunConfiguration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FullTests {

    @Test
    void fullTestALexer() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                // All we are doing is checking that an error is not thrown
                RunConfiguration runConfig = new RunConfiguration();
                runConfig.setOut(System.out);

                runConfig.setRunLexer(true);
                runConfig.setRunParser(false);
                runConfig.setRunInterpreter(false);

                String input = "4 * 2 + 5";

                Main.run(input, runConfig);
            }
        });
    }

    @Test
    void fullTestAParser() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                // All we are doing is checking that an error is not thrown
                RunConfiguration runConfig = new RunConfiguration();
                runConfig.setOut(System.out);

                runConfig.setRunLexer(true);
                runConfig.setRunParser(true);
                runConfig.setRunInterpreter(false);

                String input = "4 * 2 + 5";

                Main.run(input, runConfig);
            }
        });
    }

    @Test
    void fullTestAInterpreter() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                // All we are doing is checking that an error is not thrown
                RunConfiguration runConfig = new RunConfiguration();
                runConfig.setOut(System.out);

                runConfig.setRunLexer(true);
                runConfig.setRunParser(true);
                runConfig.setRunInterpreter(true);

                String input = "4 * 2 + 5";

                Main.run(input, runConfig);
            }
        });
    }
}
