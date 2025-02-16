package org.tessera_lang.parser;

import org.tessera_lang.Main;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

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
            ParserASTNodeInteger nodeConstant = new ParserASTNodeInteger();
            nodeConstant.setValue(Integer.valueOf(token.getValue()));
            currNode = addNode(currNode, nodeConstant);
            nodeConstant.setOriginToken(token);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_ADD) {
            // TODO: make this better
            ParserASTNodeBinaryOperation nodeBinaryOperation = new ParserASTNodeBinaryOperation();
            nodeBinaryOperation.setOperatorType(ParserASTNodeBinaryOperation.OperatorType.ADD);
            currNode = addNode(currNode, nodeBinaryOperation);
            nodeBinaryOperation.setOriginToken(token);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_MULTIPLY) {
            ParserASTNodeBinaryOperation nodeBinaryOperation = new ParserASTNodeBinaryOperation();
            nodeBinaryOperation.setOperatorType(ParserASTNodeBinaryOperation.OperatorType.MULTIPLY);
            currNode = addNode(currNode, nodeBinaryOperation);
            nodeBinaryOperation.setOriginToken(token);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_SUBTRACT) {
            ParserASTNodeBinaryOperation nodeBinaryOperation = new ParserASTNodeBinaryOperation();
            nodeBinaryOperation.setOperatorType(ParserASTNodeBinaryOperation.OperatorType.SUBTRACT);
            currNode = addNode(currNode, nodeBinaryOperation);
            nodeBinaryOperation.setOriginToken(token);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_DIVIDE) {
            ParserASTNodeBinaryOperation nodeBinaryOperation = new ParserASTNodeBinaryOperation();
            nodeBinaryOperation.setOperatorType(ParserASTNodeBinaryOperation.OperatorType.DIVIDE);
            currNode = addNode(currNode, nodeBinaryOperation);
            nodeBinaryOperation.setOriginToken(token);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_RETURN) {
            currNode = addNode(currNode, new ParserASTNodeReturn());
        }

        return currNode;
    }

    public static ParserASTNode addNode(ParserASTNode currNode, ParserASTNode newNode) {
        if (currNode == null) {
            // Node is null
            return newNode;
        } else if (currNode.getLeft() == null) {
            currNode.setLeft(newNode);
            newNode.setParent(currNode);

            return currNode;
        }  else if (currNode.getRight() == null) {
            currNode.setRight(newNode);
            newNode.setParent(currNode);

            return currNode.getLeft();
        }

        return newNode;
    }

    public static ParserASTNode getHeadRecursive(ParserASTNode node) {
        if (node ==  null) {
            return null;
        } else if (node.getParent() == null) {
            return node;
        } else {
            return getHeadRecursive(node.getParent());
        }
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
}
