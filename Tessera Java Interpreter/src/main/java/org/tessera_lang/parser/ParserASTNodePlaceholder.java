package org.tessera_lang.parser;

import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.interpreter.InterpreterStackIdentifierContext;
import org.tessera_lang.interpreter.InterpreterType;
import org.tessera_lang.interpreter.InterpreterValue;

public class ParserASTNodePlaceholder extends ParserASTNode {

    @Override
    public boolean isValid() throws InterpreterException {
        // TODO: implement this the correct way
        // YOLO!
        return true;
    }

    @Override
    public boolean hasValue(InterpreterStackIdentifierContext context) throws InterpreterException {
        return (left != null && left.hasValue(context)) || (right != null && right.hasValue(context));
    }

    @Override
    public boolean hasType(InterpreterStackIdentifierContext context) throws InterpreterException {
        return (left != null && left.hasType(context)) || (right != null && right.hasType(context));
    }

    @Override
    public InterpreterType getType(InterpreterStackIdentifierContext context) throws InterpreterException {
        if (left != null && left.hasType(context)) {
            return left.getType(context);
        } else if (right != null && right.hasType(context)) {
            return right.getType(context);
        }

        return InterpreterType.NONE;
    }

    @Override
    public InterpreterValue getValue(InterpreterStackIdentifierContext context) throws InterpreterException {
        if (getLeft() != null && getLeft().getValue(context) != null) {
            return getLeft().getValue(context);
        }
        if (getRight() != null && getRight().getValue(context) != null) {
            return getRight().getValue(context);
        }

        return null;
    }

}
