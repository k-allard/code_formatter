package ru.format.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import ru.format.exceptions.CloseException;
import ru.format.exceptions.ReaderException;

public class FileReader implements IReader {

    private final FileInputStream inputStream;
    private final Reader reader;
    private char ch;

    //TODO изменить логику - например, прочитать и сохранить первый символ в конструкторе и т.д.

    public FileReader(String filename) throws ReaderException {
        try {
            this.inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new ReaderException("Reader exception: file not found");
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
            throw new ReaderException("Reader exception");
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
            throw new CloseException("Reader inputStream.close() exception");
        }
    }
}
