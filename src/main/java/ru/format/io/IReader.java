package ru.format.io;

import ru.format.exceptions.ReaderException;

public interface IReader extends IClosable {

    boolean hasChars() throws ReaderException;

    char readChar() throws ReaderException;

    void clearBuffer() throws IllegalAccessException;

}
