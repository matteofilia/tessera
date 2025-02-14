package org.tessera_lang;

import java.util.ArrayList;

public class ParserASTNodeFunction extends ParserASTNode {

    private ParserASTNode returnToken;
    private ParserASTNode functionName;
    private ParserASTNode statement;

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list) throws ParserException {
        expect(list, LexerTokens.TOKEN_FUNCTION);
        returnToken = parse(list);
        functionName = parse(list);
        expect(list, LexerTokens.TOKEN_OPEN_PARENTHESIS);
        expect(list, LexerTokens.TOKEN_CLOSE_BRACE);
        expect(list, LexerTokens.TOKEN_OPEN_BRACE);
        statement = parse(list);
        expect(list, LexerTokens.TOKEN_CLOSE_BRACE);

        return this;
    }

    public ArrayList<ParserASTNode> getChildren() {
        ArrayList<ParserASTNode> list = new ArrayList<>();
        list.add(returnToken);
        list.add(functionName);
        list.add(statement);
        return list;
    }
}
