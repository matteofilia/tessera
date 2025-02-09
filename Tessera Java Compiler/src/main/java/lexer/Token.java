package lexer;

import java.util.regex.Pattern;

public class Token {

    private Pattern pattern;
    private String name;

    public Token(Pattern pattern, String name){
        this.pattern = pattern;
        this.name = name;
    }
}
