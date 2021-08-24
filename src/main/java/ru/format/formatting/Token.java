package ru.format.formatting;

import ru.format.formatting.interfaces.IToken;

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
}
