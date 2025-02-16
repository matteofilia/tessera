package org.tessera_lang.parser;

import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.interpreter.InterpreterType;
import org.tessera_lang.interpreter.InterpreterValue;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

import java.util.ArrayList;

public class ParserASTNode {

    protected ParserASTNode left;
    protected ParserASTNode right;
    protected ParserASTNode parent;

    private LexerTokenIdentifier identifier = null;

    private LexerToken originToken;

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

    public ParserASTNode getParent() {
        return parent;
    }

    public void setParent(ParserASTNode parent) {
        this.parent = parent;
    }

    public boolean hasValue() throws InterpreterException {
        return false;
    }

    public boolean hasType() throws InterpreterException {
        return false;
    }

    public InterpreterValue getValue() throws InterpreterException {
        return null;
    }

    public InterpreterType getType() throws InterpreterException {
        return null;
    }

    public boolean isValid() throws InterpreterException {
        return true;
    }

    public LexerToken getOriginToken() {
        return originToken;
    }

    public void setOriginToken(LexerToken originToken) {
        this.originToken = originToken;
    }
}
