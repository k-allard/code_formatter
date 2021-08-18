package ru.format.parser;

public class StringReader implements IReader {
    @Override
    public boolean hasChars() {
        return false;
    }

    @Override
    public char readChar() {
        return 0;
    }

    @Override
    public void close() throws Exception {

    }
}
