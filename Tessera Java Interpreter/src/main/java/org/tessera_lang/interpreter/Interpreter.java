package org.tessera_lang.interpreter;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.parser.Parser;
import org.tessera_lang.parser.ParserASTNode;

import java.util.ArrayList;

public class Interpreter {

    // This context is where global variables go
    private InterpreterStackIdentifierContext context;

    public Interpreter() {
        context = new InterpreterStackIdentifierContext();
    }

    /**
     * Runs the interpreter
     * @param trees A list of ASTs
     * @throws InterpreterException
     */
    public void run(ArrayList<ParserASTNode> trees, RunConfiguration runConfig) throws InterpreterException {
        // TODO: remove
        context.put("weed", new InterpreterValueInt(420));

        for (ParserASTNode tree : trees) {
            // TODO: double check if this is needed
            tree = Parser.getHeadRecursive(tree);

            if (tree == null) {
                runConfig.getOut().println("NULL");
                return;
            }

            if (!tree.isValid()) {
                runConfig.getOut().println("Tree is not valid!");
                throw new InterpreterException("Tree is not valid!");
            }

            if (tree.hasValue()) {
                runConfig.getOut().println(tree.getValue().toString());
            } else {
                runConfig.getOut().println("NO VALUE");
            }
        }
    }
}
