package ru.format.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import ru.format.exceptions.ReaderException;

public class FileReaderMy implements IClosable, IReader {

    private final FileInputStream inputStream;
    private final Reader reader;
    private char ch;

    @Override
    public boolean hasChars() throws ReaderException {
        int chInt;
        try {
            if ((chInt = reader.read()) != -1) {
                ch = (char) chInt;
                return true;
            }
        } catch (IOException e) {
            throw new ReaderException("Reader exception");
        }
        return false;
    }

    @Override
    public char readChar() {
        return (ch);
    }

    @Override
    public void close() throws ReaderException {
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new ReaderException("Reader inputStream.close() exception");
        }
    }

    public FileReaderMy(String filename) throws ReaderException {
        try {
            this.inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new ReaderException("Reader exception: file not found");
        }
        this.reader = new InputStreamReader(this.inputStream);
    }
}
