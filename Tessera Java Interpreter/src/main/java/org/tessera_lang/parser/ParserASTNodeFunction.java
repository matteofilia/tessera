package org.tessera_lang.parser;

import org.tessera_lang.RunConfiguration;
import org.tessera_lang.lexer.LexerToken;
import org.tessera_lang.lexer.LexerTokenIdentifier;
import org.tessera_lang.lexer.LexerTokens;

import java.util.ArrayList;

public class ParserASTNodeFunction extends ParserASTNodePlaceholder {

    private ParserASTNode returnToken;
    private ParserASTNode functionName;
    private ParserASTNode statement;

    @Override
    public ParserASTNode parse(ArrayList<LexerToken> list, RunConfiguration runConfig) throws ParserException {
        expect(list, LexerTokenIdentifier.TOKEN_FUNCTION);
        returnToken = parse(list, runConfig);
        functionName = parse(list, runConfig);
        expect(list, LexerTokenIdentifier.TOKEN_OPEN_PARENTHESIS);
        expect(list, LexerTokenIdentifier.TOKEN_CLOSE_BRACE);
        expect(list, LexerTokenIdentifier.TOKEN_OPEN_BRACE);
        statement = parse(list, runConfig);
        expect(list, LexerTokenIdentifier.TOKEN_CLOSE_BRACE);

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
