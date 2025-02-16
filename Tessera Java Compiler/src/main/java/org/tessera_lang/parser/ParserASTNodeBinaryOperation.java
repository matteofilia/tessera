package org.tessera_lang.parser;

import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.interpreter.InterpreterType;
import org.tessera_lang.interpreter.InterpreterValue;
import org.tessera_lang.interpreter.InterpreterValueInt;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;

import java.util.ArrayList;

import static org.tessera_lang.parser.ParserASTNodeBinaryOperation.OperatorType.*;

public class ParserASTNodeBinaryOperation extends ParserASTNode {
    public void setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
    }

    public enum OperatorType {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        MODULUS,
        POWER,

        AND,
        OR
    }

    private OperatorType operatorType;

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        return this;
    }

    @Override
    public LexerTokenIdentifier getIdentifier() {
        // TODO: wtf is this garbage code?


        if (operatorType == ADD) {
            return LexerTokenIdentifier.TOKEN_ADD;
        } else if (operatorType == SUBTRACT) {
            return LexerTokenIdentifier.TOKEN_SUBTRACT;
        } else if (operatorType ==  MULTIPLY) {
            return LexerTokenIdentifier.TOKEN_MULTIPLY;
        } else if (operatorType == DIVIDE) {
            return LexerTokenIdentifier.TOKEN_DIVIDE;
        }

        return LexerTokenIdentifier.TOKEN_ADD;
    }

    @Override
    public boolean isValid() throws InterpreterException {
        boolean valid = left != null && left.isValid() && right != null && right.isValid();

        if (!valid) {
            throw new InterpreterException("Invalid");
        } else {
            return true;
        }
    }

    @Override
    public boolean hasValue() throws InterpreterException {
        return left.hasValue() && right.hasValue();
    }

    @Override
    public boolean hasType() throws InterpreterException {
        return left.hasType() && right.hasType();
    }

    @Override
    public InterpreterType getType() throws InterpreterException {
        if (!left.hasType() || !right.hasType()) {
            return InterpreterType.NONE;
        }

        InterpreterType typeLeft = left.getType();
        InterpreterType typeRight = right.getType();

        if (typeLeft != typeRight) {
            throw new InterpreterException("Type Mismatch!");
        } else {
            return typeLeft;
        }
    }

    @Override
    public InterpreterValue getValue() throws InterpreterException {
        if (getType() == InterpreterType.INTEGER) {
            InterpreterValueInt a = (InterpreterValueInt) getLeft().getValue();
            InterpreterValueInt b = (InterpreterValueInt) getRight().getValue();

            if (operatorType == ADD) {
                return InterpreterValueInt.add(a, b);
            } else if (operatorType == SUBTRACT) {
                return InterpreterValueInt.subtract(a, b);
            } else if (operatorType == MULTIPLY) {
                return InterpreterValueInt.multiply(a, b);
            } else if (operatorType == DIVIDE) {
                return InterpreterValueInt.divide(a, b);
            }
        }

        return null;
    }
}
