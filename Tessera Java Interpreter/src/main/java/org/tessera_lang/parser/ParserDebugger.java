package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.lexer.LexerDebugger;

public class ParserDebugger {

    public static void printDebugTraversal(ParserASTNode node, RunConfiguration runConfig) {
        if (runConfig.shouldBeVeryVerbose()) {
            if (node.getParent() == null) {
                // Head Node
                runConfig.getOut().println(LexerDebugger.tokenDebugString(node.getOriginToken()));
            }
            if (node.getLeft() != null) {
                runConfig.getOut().println(LexerDebugger.tokenDebugString(node.getLeft().getOriginToken()));
            }
            if (node.getRight() != null) {
                runConfig.getOut().println(LexerDebugger.tokenDebugString(node.getRight().getOriginToken()));
            }
        }

        if (node.getLeft() != null) {
            printDebugTraversal(node.getLeft(), runConfig);
        }
    }

    public static void printNodeDebugOutput(ParserASTNode node, int level, String leftOrRight, RunConfiguration runConfig) {
        if (node != null) {
            StringBuilder output = new StringBuilder();

            int counter = 0;
            while (counter != level) {
                output.append("- ");
                counter++;
            }

            if (runConfig.shouldBeVerbose() || runConfig.shouldDisplayParserOnly()) {
                if (node.getOriginToken() == null) {
                    return;
                }

                String name = node.getOriginToken().getIdentifier().getName();

                if (node.getOriginToken().hasValue()) {
                    String value = node.getOriginToken().getValue();
                    runConfig.getOut().println(output.toString() + name + " ("+value+")"+" [" + leftOrRight + "]");
                } else {
                    runConfig.getOut().println(output.toString() + name + " [" + leftOrRight + "]");
                }
            }

            if (node.getLeft() != null) {
                printNodeDebugOutput(node.getLeft(), level + 1, "Left", runConfig);
            }
            if (node.getRight() != null) {
                printNodeDebugOutput(node.getRight(), level + 1, "Right", runConfig);
            }
        }
    }

    public static void printDebugOutput(ParserASTNode anyNode, RunConfiguration runConfig) {
        if (runConfig.shouldBeVerbose() || runConfig.shouldDisplayParserOnly()) {
            if (!runConfig.shouldDisplayParserOnly()) runConfig.getOut().print("\n");
            runConfig.getOut().println("AST Tree Debug:");

            printNodeDebugOutput(Parser.getHeadRecursive(anyNode), 0, "Head", runConfig);
            if (!runConfig.shouldDisplayParserOnly()) runConfig.getOut().print("\n");
        }
    }
}
