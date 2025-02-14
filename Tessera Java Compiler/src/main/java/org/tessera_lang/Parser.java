package org.tessera_lang;

import java.util.ArrayList;

public class Parser {

    public static void parseNode(ArrayList<LexerToken> list) throws ParserException {
        // TODO: create better function
    }

    private static ParserASTNode awfulParseNode(LexerToken token, ParserASTNode currNode) throws ParserException {
        // TODO: fix terrible WIP function
        if (Main.BE_VERBOSE) {
            System.out.println("Parsing token "+token.getId()+" to AST tree");
        }

        if (token.getId().equals(LexerTokens.TOKEN_CONSTANT)) {
            currNode = addNode(currNode, new ParserASTNodeConstant());
        } else if (token.getId().equals(LexerTokens.TOKEN_ADD)) {
            // TODO: make this better
            currNode = addNode(currNode, new ParserASTNodeAddition());
        }

        return currNode;
    }

    public static ParserASTNode addNode(ParserASTNode currNode, ParserASTNode newNode) {
        if (currNode == null) {
            // Node is null
            return newNode;
        } else if (currNode.getRight() == null) {
            currNode.setRight(newNode);
            newNode.setParent(currNode);

            return currNode;
        }  else if (currNode.getParent() == null) {
            currNode.setParent(newNode);
            newNode.setLeft(currNode);

            return newNode;
        }

        return newNode;
    }

    public static ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {

        // TODO: parse stuff
        ParserASTNode head = null;
        LexerToken token = null;
        while (!list.isEmpty()) {
            token = list.remove(0);
            head = awfulParseNode(token, head);
        }

        return head;
    }

    public static void expect(ArrayList<LexerToken> list, String expectedValue) throws ParserException {
        if (list.isEmpty()) {
            throw new ParserException();
        }

        LexerToken token = list.remove(0);

        if (!token.getId().equals(expectedValue)) {
            throw new ParserException();
        }
    }

    public static String expectRawValue(ArrayList<LexerToken> list) throws ParserException{
        if (list.isEmpty()) {
            throw new ParserException();
        }

        Object obj = list.remove(0);
        if (obj instanceof LexerValue) {
            return ((LexerValue)obj).getValue();
        } else {
            throw new ParserException();
        }
    }

    public static ParserASTNode parseInput(ArrayList<LexerToken> list) throws ParserException {
        return parse(list);
    }


    public static void parseFile(String inputFile, String outputFile) throws ParserException {
        // TODO
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
