package org.tessera_lang.parser;

import org.tessera_lang.Main;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

import java.util.ArrayList;

public class Parser {

    public static void parseNode(ArrayList<LexerToken> list) throws ParserException {
        // TODO: create better function
    }

    private static ParserASTNode reallyAwfulParseToken(LexerToken token, ParserASTNode parent) throws ParserException {
        if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_INTEGER) {
            ParserASTNodeInteger nodeConstant = new ParserASTNodeInteger();
            nodeConstant.setValue(Integer.valueOf(token.getValue()));

            nodeConstant.setOriginToken(token);
            nodeConstant.setParent(parent);

            return nodeConstant;
        }

        return null;
    }

    private static ParserASTNode reallyAwfulParseNode(ArrayList<LexerToken> list, ParserASTNode currNode) throws ParserException {
        // TODO: fix terrible WIP function

        LexerToken currToken = null;
        LexerToken nextToken = null;
        LexerToken nextNextToken = null;

        if (!list.isEmpty()) {
            currToken = list.remove(0);

            if (Main.BE_VERBOSE) {
                System.out.println("Parsing token "+currToken.getIdentifier().getName()+" to AST tree");
            }
        } else {
            throw new ParserException();
        }

        if (list.size() == 2) {
            nextToken = list.get(0);
        }
        if (list.size() > 2) {
            nextToken = list.get(0);
            nextNextToken = list.get(1);
        }

        if (nextToken != null && nextToken.getIdentifier() == LexerTokenIdentifier.TOKEN_ADD) {
            // TODO: make this better
            ParserASTNodeBinaryOperation nodeBinaryOperation = new ParserASTNodeBinaryOperation();
            nodeBinaryOperation.setOperatorType(ParserASTNodeBinaryOperation.OperatorType.ADD);
            currNode = addNode(currNode, nodeBinaryOperation);
            nodeBinaryOperation.setOriginToken(nextToken);

            if (nextNextToken != null) {
                currNode.setLeft(reallyAwfulParseToken(nextNextToken, currNode));
            }
            currNode.setRight(reallyAwfulParseToken(currToken, currNode));
        } else if (currToken.getIdentifier() == LexerTokenIdentifier.TOKEN_INTEGER) {
            ParserASTNodeInteger nodeConstant = new ParserASTNodeInteger();
            nodeConstant.setValue(Integer.valueOf(currToken.getValue()));
            currNode = addNode(currNode, nodeConstant);
            nodeConstant.setOriginToken(currToken);
        }

        /*
        } else if (currToken.getIdentifier() == LexerTokenIdentifier.TOKEN_MULTIPLY) {
            ParserASTNodeBinaryOperation nodeBinaryOperation = new ParserASTNodeBinaryOperation();
            nodeBinaryOperation.setOperatorType(ParserASTNodeBinaryOperation.OperatorType.MULTIPLY);
            currNode = addNode(currNode, nodeBinaryOperation);
            nodeBinaryOperation.setOriginToken(currToken);
        } else if (currToken.getIdentifier() == LexerTokenIdentifier.TOKEN_SUBTRACT) {
            ParserASTNodeBinaryOperation nodeBinaryOperation = new ParserASTNodeBinaryOperation();
            nodeBinaryOperation.setOperatorType(ParserASTNodeBinaryOperation.OperatorType.SUBTRACT);
            currNode = addNode(currNode, nodeBinaryOperation);
            nodeBinaryOperation.setOriginToken(currToken);
        } else if (currToken.getIdentifier() == LexerTokenIdentifier.TOKEN_DIVIDE) {
            ParserASTNodeBinaryOperation nodeBinaryOperation = new ParserASTNodeBinaryOperation();
            nodeBinaryOperation.setOperatorType(ParserASTNodeBinaryOperation.OperatorType.DIVIDE);
            currNode = addNode(currNode, nodeBinaryOperation);
            nodeBinaryOperation.setOriginToken(currToken);
        } else if (currToken.getIdentifier() == LexerTokenIdentifier.TOKEN_RETURN) {
            currNode = addNode(currNode, new ParserASTNodeReturn());
        }
        */


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

    public static ParserASTNode convertTokenToNode(LexerToken token) {
        ParserASTNode node = null;

        if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_ADD){
            node = new ParserASTNodeBinaryOperation();
            ((ParserASTNodeBinaryOperation)node).setOperatorType(ParserASTNodeBinaryOperation.OperatorType.ADD);
        } if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_SUBTRACT){
            node = new ParserASTNodeBinaryOperation();
            ((ParserASTNodeBinaryOperation)node).setOperatorType(ParserASTNodeBinaryOperation.OperatorType.SUBTRACT);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_MULTIPLY){
            node = new ParserASTNodeBinaryOperation();
            ((ParserASTNodeBinaryOperation)node).setOperatorType(ParserASTNodeBinaryOperation.OperatorType.MULTIPLY);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_DIVIDE){
            node = new ParserASTNodeBinaryOperation();
            ((ParserASTNodeBinaryOperation)node).setOperatorType(ParserASTNodeBinaryOperation.OperatorType.DIVIDE);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_DOUBLE_MULTIPLY){
            node = new ParserASTNodeBinaryOperation();
            ((ParserASTNodeBinaryOperation)node).setOperatorType(ParserASTNodeBinaryOperation.OperatorType.POWER);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_MODULUS){
            node = new ParserASTNodeBinaryOperation();
            ((ParserASTNodeBinaryOperation)node).setOperatorType(ParserASTNodeBinaryOperation.OperatorType.MODULUS);
        } else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_INTEGER){
            node = new ParserASTNodeInteger();
            ((ParserASTNodeInteger) node).setValue(Integer.parseInt(token.getValue()));
        }

        // Don't forget to add the origin token
        if (node != null) {
            node.setOriginToken(token);
        }

        return node;
    }

    public static void addNodeLeftOnly(ParserASTNode head, ParserASTNode newNode) {
        if (head.getLeft() == null) {
            head.setLeft(newNode);
            newNode.setParent(head);
        } else {
            addNodeLeftOnly(head.getLeft(), newNode);
        }
    }

    public static void addNodeRightOnly(ParserASTNode head, ParserASTNode newNode) {
        if (head.getLeft() == null) {
            head.setLeft(newNode);
            newNode.setParent(head);
        } else if (head.getRight() == null) {
            head.setRight(newNode);
            newNode.setParent(head);
        } else {
            addNodeRightOnly(head.getLeft(), newNode);
        }
    }

    public static ParserASTNode twinPassAddNodes(ArrayList<LexerToken> list, ParserASTNode head) throws ParserException {
        ArrayList<LexerToken> firstPassList = new ArrayList<>();
        ArrayList<LexerToken> secondPassList = new ArrayList<>();

        for (LexerToken token : list) {
            // First Pass Items
            if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_ADD) firstPassList.add(token);
            else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_SUBTRACT) firstPassList.add(token);
            else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_MULTIPLY) firstPassList.add(token);
            else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_DIVIDE) firstPassList.add(token);
            else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_DOUBLE_MULTIPLY) firstPassList.add(token);
            else if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_MODULUS) firstPassList.add(token);

            // Second Pass Items
            if (token.getIdentifier() == LexerTokenIdentifier.TOKEN_INTEGER) secondPassList.add(token);
        }

        // Error Checking
        if (secondPassList.size() > firstPassList.size()+1) {
            throw new ParserException("Parser Exception: too many items", secondPassList.get(secondPassList.size()-1));
        } else if (secondPassList.size() <= firstPassList.size()) {
            throw new ParserException("Parser Exception: too few items", secondPassList.get(secondPassList.size()-1));
        }

        // Set head to null
        head = null;

        // Construct First Pass, Left Assignment Only
        for (LexerToken tokenFirst : firstPassList) {
            ParserASTNode node = convertTokenToNode(tokenFirst);

            if (head == null){
                head = node;
            } else {
                addNodeLeftOnly(head, node);
            }
        }

        // Construct Second Pass, Right Assignment Only
        for (LexerToken tokenSecond : secondPassList) {
            ParserASTNode node = convertTokenToNode(tokenSecond);
            addNodeRightOnly(head, node);
        }

        return head;
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

        ParserASTNode head = new ParserASTNodePlaceholder();
        head.setOriginToken(new LexerToken(LexerTokenIdentifier.TOKEN_HEAD));

        try {
            head = twinPassAddNodes(list, head);
        } catch (ParserException e) {
            e.print();
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
