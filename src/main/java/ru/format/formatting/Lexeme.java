package ru.format.formatting;

public class Lexeme {

    public String       value;
    public LexemeType   lexemeType;
    public int          level;

    public Lexeme(LexemeType lexemeType, int level) {
        this.value      = "";
        this.lexemeType = lexemeType;
        this.level      = level;
    }

    public Lexeme(String value, int level) {
        this.value      = value;
        this.lexemeType = LexemeType.TEXT;
        this.level      = level;
    }
}
