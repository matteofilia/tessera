package org.tessera_lang.lexer;

import org.tessera_lang.Main;
import org.tessera_lang.RunConfiguration;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.tessera_lang.lexer.LexerTokenIdentifier.*;

public class LexerTokens {

    private static boolean loaded = false;
    private static final ArrayList<LexerTokenTemplate> allTemplates = new ArrayList<>();

    public static void init() {
        // NOTE: order is important here

        // Test Tokens For Debug
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("test\\b"), TOKEN_TEST));

        // Data Type Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("[0-9]+\\b"),  TOKEN_INTEGER, true));

        // Special Keyword Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("function\\b"),  TOKEN_FUNCTION));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("int\\b"),  TOKEN_INT));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("void\\b"),  TOKEN_VOID));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("return\\b"),  TOKEN_RETURN));

        // Control Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("if\\b"),  TOKEN_IF));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("then\\b"), TOKEN_THEN));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("elif\\b"), TOKEN_ELIF));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("else\\b"), TOKEN_ELSE));

        // Syntax Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\("), TOKEN_OPEN_PARENTHESIS));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\)"), TOKEN_CLOSE_PARENTHESIS));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\{"), TOKEN_OPEN_BRACE));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\}"), TOKEN_CLOSE_BRACE));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\["), TOKEN_OPEN_SQUARE_BRACE));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\]"), TOKEN_CLOSE_SQUARE_BRACE));

        // More Syntax Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile(";"),  TOKEN_SEMICOLON));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile(":"),  TOKEN_COLON));

        // Identifier Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("[a-zA-Z_]\\w*\\b"),  TOKEN_IDENTIFIER, true));

        // Special Function Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("print\\b"),  TOKEN_PRINT));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("exit\\b"),  TOKEN_EXIT));

        // Equality Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\="),  TOKEN_EQUALS));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\=\\="),  TOKEN_DOUBLE_EQUALS));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\=\\=\\="),  TOKEN_TRIPLE_EQUALS));

        // Operation Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\+"),  TOKEN_ADD));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\-"),  TOKEN_SUBTRACT));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\*"), TOKEN_MULTIPLY));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\*\\*"), TOKEN_DOUBLE_MULTIPLY));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\/"), TOKEN_DIVIDE));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("\\%"), TOKEN_MODULUS));

        // Boolean Logic Tokens
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("and\\b"), TOKEN_AND));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("or\\b"), TOKEN_OR));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("not\\b"), TOKEN_NOT));

        // Variables
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("global var\\b"), TOKEN_GLOBAL_VAR));
        LexerTokens.add(new LexerTokenTemplate(Pattern.compile("local var\\b"), TOKEN_LOCAL_VAR));
    }

    public static void add(LexerTokenTemplate template) {
        allTemplates.add(template);
    }

    public static ArrayList<LexerTokenTemplate> getAllTemplates() {
        if (!loaded) {
            init();
            loaded = true;
        }

        return allTemplates;
    }

    public static ArrayList<LexerToken> checkMatches(String line, RunConfiguration runConfig) throws LexerException {
        ArrayList<LexerToken> list = new ArrayList<>();
        if (runConfig.shouldBeVerbose()) runConfig.getOut().println("Checking match: "+line);

        // Check all tokens for match
        int columnIndex = 0;
        LexerTokenTemplate longestMatchingTemplate = null;
        int maxLength = 0;
        String value = null;

        while (columnIndex < line.length()) {
            if (Character.isWhitespace(line.charAt(columnIndex))) {
                columnIndex++;
                continue;
            } else if (line.charAt(columnIndex) == '#') {
                // Comment, ignore remaining input
                columnIndex = line.length();
                continue;
            }

            for (LexerTokenTemplate template : getAllTemplates()) {
                Matcher matcher = template.getPattern().matcher(line);

                if (matcher.find(columnIndex) && matcher.start() == columnIndex) {
                    if (runConfig.shouldRunLexer() && runConfig.shouldBeVeryVerbose()) runConfig.getOut().println("Index = " + columnIndex);

                    int length = matcher.end() - matcher.start();
                    if (runConfig.shouldBeVerbose()) {
                        runConfig.getOut().println("Potential match found! ("+template.getIdentifier() + ")");
                    }


                    if (length > maxLength) {
                        longestMatchingTemplate = template;
                        value = line.substring(matcher.start(), matcher.end());

                        maxLength = length;
                    }
                }
            }

            if (longestMatchingTemplate != null) {
                if (runConfig.shouldBeVerbose()) runConfig.getOut().println("Template match found!"+" ("+ longestMatchingTemplate.getIdentifier().getName()+")");

                LexerToken token = new LexerToken(longestMatchingTemplate.getIdentifier());
                token.setOriginColumn(columnIndex);
                token.setOriginLine(line);

                if (longestMatchingTemplate.shouldHaveValue()) {
                    token.setValue(value);

                    if (runConfig.shouldBeVerbose()) LexerDebugger.tokenDebugString(token);
                }

                list.add(token);
                columnIndex = columnIndex + maxLength;

                longestMatchingTemplate = null;
                value = null;
                maxLength = 0;
            } else {
                // No match
                throw new LexerException();
            }
        }

        return list;
    }

    private LexerTokens() throws Exception {
        throw new Exception("Not Allowed");
    }
}
