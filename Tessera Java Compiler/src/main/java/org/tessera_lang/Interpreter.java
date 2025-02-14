package org.tessera_lang;

public class Interpreter {

    private static void visit(ParserASTNode node) {
        // For now, just output node information
        for (ParserASTNode child : node.getChildren()) {
            visit(child);

            if (Main.BE_VERBOSE) {
                if (node instanceof ParserASTNodeConstant) {
                    System.out.println(node.getIdentifier() + " : " + ((ParserASTNodeConstant)node).getValue());
                } else {
                    System.out.println(node.getIdentifier());
                }
            }
        }
    }

    public static void run(ParserASTNode tree) {
        if (tree != null) {
            visit(tree);
        }
    }

    public static void assembleFile(String inputFile, String outputFile) {
        // TODO
    }
}
