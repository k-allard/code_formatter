package ru.format.lexer;

import ru.format.exceptions.CloseException;
import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;

public class PostponeReader implements IReader {
    Signal signal;

    public PostponeReader() {
        signal = new Signal();
    }

    @Override
    public void close() throws CloseException {

    }

    @Override
    public boolean hasChars() throws ReaderException {
        return false;
    }

    @Override
    public char readChar() throws ReaderException {
        return 0;
    }
}
