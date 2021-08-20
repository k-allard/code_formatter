package ru.format.io;

import ru.format.exceptions.CloseException;

public class StringWriter implements IWriter {

    private final StringBuilder outString;

    public StringWriter() {
        outString = new StringBuilder();
    }

    public StringWriter(String outString) {
        this.outString = new StringBuilder(outString);
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