package ru.format.lexer;

public class TokenBuilder {

    private String name;
    private StringBuilder lexeme;

    public TokenBuilder() {
        lexeme = new StringBuilder();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void appendLexeme(char ch) {
        lexeme.append(ch);
    }

    Token buildToken() {
        return  new Token(name, lexeme.toString());
    }
}
