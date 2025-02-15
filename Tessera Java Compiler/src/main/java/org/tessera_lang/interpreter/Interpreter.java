package org.tessera_lang.interpreter;

import org.tessera_lang.lexer.LexerTokens;
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

        // TODO

        // TODO
        return 0;
    }

    public static void run(ParserASTNode tree) {
        if (tree != null) {
            visit(tree);
        }
    }
}
