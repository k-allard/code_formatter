package ru.format.parser;

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
    public char readChar() {
        return inputString.charAt(position++);
    }
}
