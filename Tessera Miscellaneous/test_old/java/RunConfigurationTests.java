import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.tessera_lang.RunConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunConfigurationTests {

    private static RunConfiguration runConfigA;
    private static RunConfiguration runConfigB;

    @BeforeAll
    public static void performTestSetup() {
        runConfigA = new RunConfiguration();
        runConfigA.setRunLexer(true);
        runConfigA.setRunParser(false);
        runConfigA.setRunInterpreter(false);

        runConfigB = new RunConfiguration();
        runConfigB.setRunLexer(true);
        runConfigB.setRunParser(true);
        runConfigB.setRunInterpreter(false);
    }

    @Test
    public void checkShouldDisplayLexerOnly(){
        assertEquals(runConfigA.shouldDisplayLexerOnly(), true);
    }

    @Test
    public void checkShouldDisplayParserOnly(){
        assertEquals(runConfigB.shouldDisplayParserOnly(), true);
    }
}
