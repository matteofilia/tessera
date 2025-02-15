package org.tessera_lang.parser;

import org.tessera_lang.Main;

public class ParserDebugger {

    public static void printDebugTraversal(ParserASTNode parserASTNode) {
        if (parserASTNode.getLeft() != null) {
            printDebugTraversal(parserASTNode.getLeft());
        }

        if (Main.BE_VERBOSE) {
            if (parserASTNode instanceof ParserASTNodeConstant) {
                System.out.println(parserASTNode.getIdentifier() + " ("+((ParserASTNodeConstant)parserASTNode).getValue()+")");
            } else {
                System.out.println(parserASTNode.getIdentifier());
            }
        }

        if (parserASTNode.getRight() != null) {
            printDebugTraversal(parserASTNode.getRight());
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
                System.out.println(output.toString() + node.getIdentifier() + " ("+leftOrRight+")");
            }

            if (node.getLeft() != null) {
                printNodeDebugOutput(node.getLeft(), level + 1, "Left");
            }
            if (node.getRight() != null) {
                printNodeDebugOutput(node.getRight(), level + 1, "Right");
            }
        }
    }

    public static void printDebugOutput(ParserASTNode head) {
        if (Main.BE_VERBOSE) {
            System.out.println("AST Tree Debug:");
        }

        printNodeDebugOutput(head, 0, "Head");
    }
}
