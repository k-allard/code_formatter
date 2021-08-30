package ru.format.lexer;

public class Context implements IContext {

    TokenBuilder tokenBuilder;
    PostponeReader postponeReader;

    public Context() {
        tokenBuilder = new TokenBuilder();
        postponeReader = new PostponeReader();
    }

    @Override
    public void appendLexeme(char ch) {

    }

    @Override
    public void setTokenName(String name) {

    }

    @Override
    public void appendPostpone(char ch) {

    }
}
