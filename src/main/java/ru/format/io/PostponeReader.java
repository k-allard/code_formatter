package ru.format.io;

import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.CloseException;
import ru.format.exceptions.ReaderException;

@Slf4j
public class PostponeReader implements IReader {

    private final StringBuilder inputString;
    private int position;

    public PostponeReader(StringBuilder inputString) {
        this.inputString = inputString;
        position = 0;
    }

    @Override
    public boolean hasChars() throws ReaderException {
        return (position < inputString.length());
    }

    @Override
    public char readChar() throws ReaderException {
        try {
            return inputString.charAt(position++);
        } catch (IndexOutOfBoundsException e) {
            log.error("StringReader.readChar() exception", e);
            throw new ReaderException("StringReader.readChar() exception");
        }
    }

    @Override
    public void close() throws CloseException {

    }
}
