package ru.format;

public class Token {
    public String value;
    public TokenType tokenType;
    public int level;

    public Token(TokenType tokenType, int level) {
        this.value = "";
        this.tokenType = tokenType;
        this.level = level;
    }

    public Token(String value, int level) {
        this.value = value;
        this.tokenType = TokenType.OTHER;
        this.level = level;
    }

    @Override
    public String toString() {
        if (tokenType == TokenType.OPEN)
            return "{";
        if (tokenType == TokenType.CLOSE)
            return "}";
        return value;
    }
}
