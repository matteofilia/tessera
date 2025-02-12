package org.tessera_lang;

import java.util.ArrayList;

public class Parser {

    public static ASTNode parse(ArrayList<LexerToken> list) {
        StringBuilder output = new StringBuilder();
        ASTNode root = new ASTNode(null);

        return output.toString();
    }

    public static void parseFile(String inputFile, String outputFile) {
        // TODO
    }

    class ASTNode {
        private ASTNode parent;

        public ASTNode(ASTNode parent) {
            this.parent = parent;
        }
    }
}
