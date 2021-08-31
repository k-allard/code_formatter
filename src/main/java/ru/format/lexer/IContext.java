package ru.format.lexer;

public interface IContext {
    void appendLexeme(char ch);

    void setTokenName(String name);

    void appendPostpone(char ch);
}
