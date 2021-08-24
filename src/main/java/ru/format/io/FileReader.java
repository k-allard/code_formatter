package ru.format.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.format.Main;
import ru.format.exceptions.CloseException;
import ru.format.exceptions.ReaderException;
import ru.format.io.interfaces.IReader;

public class FileReader implements IReader {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private final FileInputStream inputStream;
    private final Reader reader;
    private int currentChar;

    public FileReader(String filename) throws ReaderException {
        try {
            this.inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            logger.error("FileReader exception: file {} not found", filename, e);
            throw new ReaderException("FileReader exception: file not found");
        }
        this.reader = new InputStreamReader(this.inputStream, StandardCharsets.UTF_8);
        try {
            currentChar = reader.read();
        } catch (IOException e) {
            logger.error("FileReader exception", e);
            throw new ReaderException("FileReader exception");
        }
    }

    @Override
    public boolean hasChars() {
        return currentChar != -1;
    }

    @Override
    public char readChar() throws ReaderException {
        if (hasChars()) {
            int previousChar = currentChar;
            try {
                currentChar = reader.read();
            } catch (IOException e) {
                logger.error("FileReader.readChar() exception", e);
                throw new ReaderException("FileReader.readChar() exception");
            }
            return (char) previousChar;
        }
        logger.error("FileReader.readChar(): You need to call hasChars() first before reading");
        throw new ReaderException("FileReader.readChar() exception: hasChars() returned false");
    }

    @Override
    public void close() throws CloseException {
        try {
            inputStream.close();
        } catch (IOException e) {
            logger.error("FileReader.close() exception", e);
            throw new CloseException("FileReader.close() exception");
        }
    }
}
