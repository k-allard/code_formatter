package ru.format.lexer;

import java.util.Objects;

public class Token implements IToken {

    private final String name;
    private final String lexeme;

    public Token(String name, String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Token token = (Token) o;
        return Objects.equals(name, token.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
