package org.tessera_lang.parser;

import org.tessera_lang.Main;
import org.tessera_lang.lexer.LexerDebugger;

public class ParserDebugger {

    public static void printDebugTraversal(ParserASTNode node) {
        if (Main.BE_VERY_VERBOSE) {
            System.out.println(LexerDebugger.tokenDebugString(node.getOriginToken()));
        }

        if (node.getLeft() != null) {
            printDebugTraversal(node.getLeft());
        }

        if (node.getRight() != null) {
            printDebugTraversal(node.getRight());
        }
    }

    public static void printNodeDebugOutput(ParserASTNode node, int level, String leftOrRight) {
        if (node != null) {
            StringBuilder output = new StringBuilder();

            int counter = 0;
            while (counter != level) {
                output.append("- ");
                counter++;
            }

            if (Main.BE_VERBOSE) {
                System.out.println(output.toString() + node.getOriginToken().getIdentifier().getName() + " ("+leftOrRight+")");
            }

            if (node.getLeft() != null) {
                printNodeDebugOutput(node.getLeft(), level + 1, "Left");
            }
            if (node.getRight() != null) {
                printNodeDebugOutput(node.getRight(), level + 1, "Right");
            }
        }
    }

    public static void printDebugOutput(ParserASTNode anyNode) {
        if (Main.BE_VERBOSE) {
            System.out.println("AST Tree Debug:");
        }

        printNodeDebugOutput(Parser.getHeadRecursive(anyNode), 0, "Head");
    }
}
