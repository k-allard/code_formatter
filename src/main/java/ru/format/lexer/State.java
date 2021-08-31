package ru.format.lexer;

public enum State {
    INITIAL,
    SPACING,
    OPEN,
    CLOSE,
    SEMICOLON,
    TERMINATED
}
