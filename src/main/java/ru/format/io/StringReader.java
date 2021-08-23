package ru.format.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.format.Main;
import ru.format.exceptions.ReaderException;

public class StringReader implements IReader {

    private final String inputString;
    private int position;
    private static final Logger logger = LoggerFactory.getLogger(StringReader.class);

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
            logger.error("StringReader.readChar() exception", e);
            throw new ReaderException("StringReader.readChar() exception");
        }
    }

    @Override
    public void close() {

    }
}
