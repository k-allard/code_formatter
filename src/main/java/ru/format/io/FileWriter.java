package ru.format.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import ru.format.exceptions.CloseException;
import ru.format.exceptions.WriterException;

public class FileWriter implements IWriter {

    private final Writer writer;

    public FileWriter(String filename) throws WriterException {   //TODO все конструкторы классов вынести вверх
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new WriterException("FileWriter exception: in FileWriter()");
        }
    }

    @Override
    public void writeChar(char ch) throws WriterException {
        try {
            writer.write(ch);
        } catch (IOException e) {
            throw new WriterException("FileWriter exception: in writeChar()");
        }
    }

    @Override
    public void close() throws CloseException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new CloseException("Reader inputStream.close() exception");
        }
    }
}
