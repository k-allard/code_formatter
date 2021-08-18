package ru.format.parser;

import ru.format.exceptions.ReaderException;

public interface IReader extends AutoCloseable {

    boolean hasChars() throws ReaderException;

    char readChar() throws ReaderException;

}
