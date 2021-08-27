package ru.format.lexer;

public enum State {
    INITIAL,
    TEXT,
    OPEN,
    CLOSE,
    SEMICOLON,
    TERMINATED
}
