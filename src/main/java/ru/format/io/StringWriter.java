package ru.format.io;

public class StringWriter implements IWriter {

    private final StringBuilder outString;

    public StringWriter() {
        outString = new StringBuilder();
    }

    @Override
    public void writeChar(char ch) {
        outString.append(ch);
    }

    public String toString() {
        return outString.toString();
    }

    @Override
    public void close() {

    }
}
