package org.tessera_lang.parser;

import org.tessera_lang.Main;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;
import org.tessera_lang.lexer.LexerTokens;

import java.util.ArrayList;

public class Parser {

    public static void parseNode(ArrayList<LexerToken> list) throws ParserException {
        // TODO: create better function
    }

    private static ParserASTNode awfulParseNode(ArrayList<LexerToken> list, ParserASTNode currNode) throws ParserException {
        // TODO: fix terrible WIP function

        LexerToken token = null;
        if (!list.isEmpty()) {
            token = list.remove(0);

            if (Main.BE_VERBOSE) {
                System.out.println("Parsing token "+token.getIdentifier().getName()+" to AST tree");
            }
        } else {
            throw new ParserException();
        }

        if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_INTEGER) {
            ParserASTNodeConstant nodeConstant = new ParserASTNodeConstant();
            nodeConstant.setValue(Integer.valueOf(token.getValue()));
            currNode = addNode(currNode, nodeConstant);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_ADD) {
            // TODO: make this better
            currNode = addNode(currNode, new ParserASTNodeAddition());
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_RETURN) {
            currNode = addNode(currNode, new ParserASTNodeReturn());
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_MULTIPLY) {
            currNode = addNode(currNode, new ParserASTNodeMultiplication());
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
        while (!list.isEmpty()) {
            head = awfulParseNode(list, head);
        }

        return head;
    }

    public static void expect(ArrayList<LexerToken> list, LexerTokenIdentifier expectedIdentifier) throws ParserException {
        if (list.isEmpty()) {
            if (Main.BE_VERBOSE) System.out.println("LexerToken list is empty! (should have value)");
            throw new ParserException();
        }

        LexerTokenIdentifier token = list.remove(0).getIdentifier();

        if (!(token == expectedIdentifier)) {
            throw new ParserException();
        }
    }

    public static String expectRawValue(ArrayList<LexerToken> list) throws ParserException {
        if (list.isEmpty()) {
            if (Main.BE_VERBOSE) System.out.println("LexerToken list is empty! (should have value)");
            throw new ParserException();
        }

        LexerToken token = list.remove(0);
        if (token.hasValue()) {
            return token.getValue();
        } else {
            if (Main.BE_VERBOSE) System.out.println("LexerToken has no value when one is expected");
            throw new ParserException();
        }
    }

    public static ParserASTNode parseInput(ArrayList<LexerToken> list) throws ParserException {
        return parse(list);
    }


    public static void parseFile(String inputFile, String outputFile) throws ParserException {
        // TODO
    }

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
