package ru.format.io;

import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.ReaderException;

@Slf4j
public class StringReader implements IReader {

    private String inputString;
    private int position;

    public StringReader(String inputString) {
        this.inputString = inputString;
        position = 0;
    }

    @Override
    public boolean hasChars() {
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
    public void clearBuffer() {
        inputString = "";
    }

    @Override
    public void close() {

    }
}
