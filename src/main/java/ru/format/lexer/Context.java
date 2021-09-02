package ru.format.lexer;

public class Context implements IContext {

    private TokenBuilder tokenBuilder;
    private final StringBuilder postponeString;

    public Context(StringBuilder postponeString) {
        this.postponeString = postponeString;
    }

    public void newTokenBuilder() {
        this.tokenBuilder = new TokenBuilder();
    }

    @Override
    public void appendLexeme(char ch) {
        tokenBuilder.appendLexeme(ch);
    }

    public TokenBuilder getTokenBuilder() {
        return tokenBuilder;
    }

    @Override
    public void setTokenName(String name) {
        tokenBuilder.setName(name);
    }

    @Override
    public void appendPostpone(char ch) {
        postponeString.append(ch);
    }
}
