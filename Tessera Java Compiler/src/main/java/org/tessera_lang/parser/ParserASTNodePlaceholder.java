package org.tessera_lang.parser;

import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.interpreter.InterpreterType;
import org.tessera_lang.interpreter.InterpreterValue;
import org.tessera_lang.interpreter.InterpreterValueInt;

import static org.tessera_lang.parser.ParserASTNodeBinaryOperation.OperatorType.*;
import static org.tessera_lang.parser.ParserASTNodeBinaryOperation.OperatorType.DIVIDE;

public class ParserASTNodePlaceholder extends ParserASTNode {

    @Override
    public boolean isValid() throws InterpreterException {
        // TODO: implement this the correct way
        // YOLO!
        return true;
    }

    @Override
    public boolean hasValue() throws InterpreterException {
        return (left != null && left.hasValue()) || (right != null && right.hasValue());
    }

    @Override
    public boolean hasType() throws InterpreterException {
        return (left != null && left.hasType()) || (right != null && right.hasType());
    }

    @Override
    public InterpreterType getType() throws InterpreterException {
        if (left != null && left.hasType()) {
            return left.getType();
        } else if (right != null && right.hasType()) {
            return right.getType();
        }

        return InterpreterType.NONE;
    }

    @Override
    public InterpreterValue getValue() throws InterpreterException {
        if (getLeft() != null && getLeft().getValue() != null) {
            return getLeft().getValue();
        }
        if (getRight() != null && getRight().getValue() != null) {
            return getRight().getValue();
        }

        return null;
    }

}
