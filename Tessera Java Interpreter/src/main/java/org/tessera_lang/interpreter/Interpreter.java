package org.tessera_lang.interpreter;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.parser.Parser;
import org.tessera_lang.parser.ParserASTNode;

import java.util.ArrayList;

public class Interpreter {

    // This context is where global variables go
    private InterpreterStackIdentifierContext rootContext;

    public Interpreter() {
        rootContext = new InterpreterStackIdentifierContext();

        // TODO: remove
        rootContext.put("weed", new InterpreterValueInt(420));
    }

    /**
     * Runs the interpreter
     * @param trees A list of ASTs
     * @throws InterpreterException
     */
    public void run(ArrayList<ParserASTNode> trees, RunConfiguration runConfig) throws InterpreterException {
        try {
            // First, start by visiting all nodes
            for (ParserASTNode tree : trees) {
                tree = Parser.getHeadRecursive(tree);
                tree.visit(rootContext, runConfig);
            }

            // Now, once nodes are visited, we can get the value of them
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

                if (tree.hasValue(rootContext)) {
                    runConfig.getOut().println(tree.getValue(rootContext).toString());
                } else {
                    runConfig.getOut().println("NO VALUE");
                }
            }
        } catch (Exception e) {
            // TODO: actually throw and catch an InterpreterException properly
            runConfig.getOut().println("Generic Interpreter Error");
            e.printStackTrace(runConfig.getOut());
        }
    }
}
