package org.tessera_lang;

import java.io.PrintStream;

public class RunConfiguration {
    private boolean beVerbose;
    private boolean beVeryVerbose;

    private boolean runLexer = false;
    private boolean runParser = false;
    private boolean runInterpreter = false;

    private boolean printCode = false;

    private boolean web = false;
    private boolean displayAsciiArt = false;

    private PrintStream out = null;

    public RunConfiguration() {
        // Do Nothing
    }

    public boolean shouldBeVerbose() {
        return beVerbose;
    }

    public void setBeVerbose(boolean beVerbose) {
        this.beVerbose = beVerbose;
    }

    public boolean shouldBeVeryVerbose() {
        return beVeryVerbose;
    }

    public void setBeVeryVerbose(boolean beVeryVerbose) {
        this.beVeryVerbose = beVeryVerbose;
    }

    public boolean shouldRunLexer() {
        return runLexer;
    }

    public void setRunLexer(boolean runLexer) {
        this.runLexer = runLexer;
    }

    public boolean shouldRunParser() {
        return runParser;
    }

    public void setRunParser(boolean runParser) {
        this.runParser = runParser;
    }

    public boolean shouldRunInterpreter() {
        return runInterpreter;
    }

    public void setRunInterpreter(boolean runInterpreter) {
        this.runInterpreter = runInterpreter;
    }

    public boolean shouldPrintCode() {
        return printCode;
    }

    public void setPrintCode(boolean printCode) {
        this.printCode = printCode;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public PrintStream getOut() {
        return out;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    /**
     * If ONLY runLexer is set, then display the lexer output
     */
    public boolean shouldDisplayLexerOnly() {
        return this.runLexer && !this.runParser && !this.runInterpreter;
    }

    /**
     * If ONLY runParser is set, then display the parser output
     */
    public boolean shouldDisplayParserOnly() {
        return this.runParser && !this.runInterpreter;
    }

    public boolean shouldDisplayAsciiArt() {
        return displayAsciiArt;
    }

    public void setDisplayAsciiArt(boolean displayAsciiArt) {
        this.displayAsciiArt = displayAsciiArt;
    }
}
