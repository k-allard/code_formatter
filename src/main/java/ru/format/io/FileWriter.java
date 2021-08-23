package ru.format.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.format.Main;
import ru.format.exceptions.CloseException;
import ru.format.exceptions.WriterException;

public class FileWriter implements IWriter {

    private final Writer writer;
    private static final Logger logger = LoggerFactory.getLogger(FileWriter.class);

    public FileWriter(String filename) throws WriterException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("FileWriter exception: in FileWriter()", e);
            throw new WriterException("FileWriter exception: in FileWriter()");
        }
    }

    @Override
    public void writeChar(char ch) throws WriterException {
        try {
            writer.write(ch);
        } catch (IOException e) {
            logger.error("FileWriter exception: in writeChar()", e);
            throw new WriterException("FileWriter exception: in writeChar()");
        }
    }

    @Override
    public void close() throws CloseException {
        try {
            writer.close();
        } catch (IOException e) {
            logger.error("Writer.close() exception", e);
            throw new CloseException("Writer.close() exception");
        }
    }
}
