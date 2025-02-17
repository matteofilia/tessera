package org.tessera_lang.parser;

import org.tessera_lang.interpreter.InterpreterException;
import org.tessera_lang.interpreter.InterpreterType;
import org.tessera_lang.interpreter.InterpreterValue;
import org.tessera_lang.interpreter.InterpreterValueInt;
import org.tessera_lang.lexer.LexerToken;

import java.util.ArrayList;

import static org.tessera_lang.parser.ParserASTNodeBinaryOperation.OperatorType.*;

public class ParserASTNodeBinaryOperation extends ParserASTNodePlaceholder {
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
