package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.interpreter.*;
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
    public ParserASTNode parse(ArrayList<LexerToken> list, RunConfiguration runConfig) throws ParserException {
        return this;
    }

    @Override
    public InterpreterValue getValue(InterpreterStackIdentifierContext context) throws InterpreterException {
        if (getType(context) == InterpreterType.INTEGER) {
            InterpreterValueInt a = (InterpreterValueInt) getRight().getValue(context);
            InterpreterValueInt b = (InterpreterValueInt) getLeft().getValue(context);

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
