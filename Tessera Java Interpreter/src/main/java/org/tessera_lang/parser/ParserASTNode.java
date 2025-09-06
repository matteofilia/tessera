package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.interpreter.InterpreterStackIdentifierContext;
import org.tessera_lang.interpreter.InterpreterType;
import org.tessera_lang.interpreter.InterpreterValue;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

import java.util.ArrayList;

public abstract class ParserASTNode {

    protected ParserASTNode left;
    protected ParserASTNode right;
    protected ParserASTNode parent;

    private LexerTokenIdentifier identifier = null;

    private LexerToken originToken;

    public ParserASTNode() {
        // Empty
    }

    public ParserASTNode parse(ArrayList<LexerToken> list, RunConfiguration runConfig) throws ParserException {
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

    public abstract boolean hasValue(InterpreterStackIdentifierContext context) throws InterpreterException;
    public abstract boolean hasType(InterpreterStackIdentifierContext context) throws InterpreterException;
    public abstract InterpreterValue getValue(InterpreterStackIdentifierContext context) throws InterpreterException;
    public abstract InterpreterType getType(InterpreterStackIdentifierContext context) throws InterpreterException;
    public abstract boolean isValid() throws InterpreterException ;

    public abstract void visit(InterpreterStackIdentifierContext context);

    public LexerToken getOriginToken() {
        return originToken;
    }

    public void setOriginToken(LexerToken originToken) {
        this.originToken = originToken;
    }
}
