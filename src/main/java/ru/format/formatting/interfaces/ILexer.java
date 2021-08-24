package ru.format.formatting.interfaces;

import ru.format.exceptions.ReaderException;

public interface ILexer {
    boolean hasMoreTokens();

    IToken readToken() throws ReaderException;
}
