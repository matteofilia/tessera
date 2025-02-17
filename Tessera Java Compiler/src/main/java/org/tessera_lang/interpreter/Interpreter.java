package org.tessera_lang.interpreter;

import org.tessera_lang.parser.Parser;
import org.tessera_lang.parser.ParserASTNode;

public class Interpreter {

    public static void run(ParserASTNode tree) {
        try {
            tree = Parser.getHeadRecursive(tree);

            if (!tree.isValid()) {
                throw new InterpreterException("Tree is not valid!");
            }

            if (tree.hasValue()) {
                System.out.println("Value: ");
                System.out.println(tree.getValue().toString());
            } else {
                System.out.println("Tree has no value");
            }

        } catch (InterpreterException e) {
            // TODO: add in token debug
            System.out.println("Error: tree is not valid!");
        }
    }
}
