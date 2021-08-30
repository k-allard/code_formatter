package ru.format.lexer;

import ru.format.exceptions.ReaderException;

public interface ILexer {
    boolean hasMoreTokens() throws ReaderException;

    IToken nextToken() throws ReaderException;
}
