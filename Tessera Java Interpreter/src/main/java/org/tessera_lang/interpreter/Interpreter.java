package org.tessera_lang.interpreter;

import org.tessera_lang.parser.Parser;
import org.tessera_lang.parser.ParserASTNode;

import java.io.PrintStream;
import java.util.ArrayList;

public class Interpreter {

    /**
     * Runs the interpreter
     * @param trees A list of ASTs
     * @throws InterpreterException
     */
    public static void run(ArrayList<ParserASTNode> trees, PrintStream output) throws InterpreterException {
        for (ParserASTNode tree : trees) {
            // TODO: double check if this is needed
            tree = Parser.getHeadRecursive(tree);

            if (tree == null) {
                output.println("NULL");
                return;
            }

            if (!tree.isValid()) {
                output.println("Tree is not valid!");
                throw new InterpreterException("Tree is not valid!");
            }

            if (tree.hasValue()) {
                output.println("Value: " + tree.getValue().toString());
            } else {
                output.println("NO VALUE");
            }
        }
    }
}
