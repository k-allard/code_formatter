package ru.format.io;

import ru.format.exceptions.ReaderException;

public class StringReader implements IReader {

    private final String inputString;
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
            throw new ReaderException("Reader readChar() exception");
        }
    }

    @Override
    public void close() {

    }
}
