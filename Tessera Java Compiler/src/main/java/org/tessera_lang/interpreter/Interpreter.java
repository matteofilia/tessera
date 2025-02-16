package org.tessera_lang.interpreter;

import org.tessera_lang.parser.Parser;
import org.tessera_lang.parser.ParserASTNode;

public class Interpreter {

    private static int visit(ParserASTNode node) {
        int leftValue = 0;
        int rightValue = 0;

        if (node.getLeft() != null) {
            leftValue = visit(node.getLeft());
        }

        if (node.getRight() != null) {
            rightValue = visit(node.getRight());
        }

        return 0;
    }

    public static void run(ParserASTNode tree) {
        try {
            if (tree != null) {
                visit(tree);
            }

            if (!tree.isValid()) {
                throw new InterpreterException("Tree is not valid!");
            }
            
        } catch (InterpreterException e) {
            // TODO: add in token debug
        }
    }
}
