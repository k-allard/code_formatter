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

public class FileReader implements IReader {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private final FileInputStream inputStream;
    private final Reader reader;
    private char ch;

    //TODO изменить логику - например, прочитать и сохранить первый символ в конструкторе и т.д.

    public FileReader(String filename) throws ReaderException {
        try {
            this.inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            logger.error("FileReader exception: file {} not found", filename, e);
            throw new ReaderException("FileReader exception: file not found");
        }
        this.reader = new InputStreamReader(this.inputStream, StandardCharsets.UTF_8);
    }

    @Override
    public boolean hasChars() throws ReaderException {  //TODO не должен читать
        int chInt;
        try {
            if ((chInt = reader.read()) != -1) {
                ch = (char) chInt;
                return true;
            }
        } catch (IOException e) {
            logger.error("FileReader.hasChars() exception", e);
            throw new ReaderException("FileReader.hasChars() exception");
        }
        return false;
    }

    @Override
    public char readChar() {
        return (ch);
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
