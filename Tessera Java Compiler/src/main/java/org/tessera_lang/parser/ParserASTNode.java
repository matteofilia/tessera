package org.tessera_lang.parser;

import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

import java.util.ArrayList;

public class ParserASTNode {

    private ParserASTNode left;
    private ParserASTNode right;
    private ParserASTNode parent;

    private LexerTokenIdentifier identifier = null;

    public ParserASTNode() {
        // Empty
    }

    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        // Do nothing
        return null;
    }

    public static void expect(ArrayList<LexerToken> list, LexerTokenIdentifier expectedIdentifier) throws ParserException {
        if (list.isEmpty()) {
            throw new ParserException();
        }

        LexerToken token = list.remove(0);

        if (!token.getIdentifier().equals(expectedIdentifier)) {
            throw new ParserException();
        }
    }

    public ArrayList<ParserASTNode> getChildren() {
        ArrayList<ParserASTNode> list = new ArrayList<>();
        list.add(left);
        list.add(right);
        return list;
    }

    public ParserASTNode getLeft() {
        return left;
    }

    public ParserASTNode getRight() {
        return right;
    }


    public void setLeft(ParserASTNode left) {
        this.left = left;
    }

    public void setRight(ParserASTNode right) {
        this.right = right;
    }

    public LexerTokenIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(LexerTokenIdentifier identifier) {
        this.identifier = identifier;
    }

    public ParserASTNode getParent() {
        return parent;
    }

    public void setParent(ParserASTNode parent) {
        this.parent = parent;
    }
}
