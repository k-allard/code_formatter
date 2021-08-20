package ru.format.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import ru.format.exceptions.WriterException;

public class FileWriterMy implements IWriter {

    private final PrintWriter outFile;

    @Override
    public void writeChar(char ch) {
        outFile.write(ch);
        outFile.flush();
    }

    public FileWriterMy(String filename) throws WriterException {
        try {
            outFile = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            throw new WriterException("Writer exception");
        }
    }

    @Override
    public void close() {
        outFile.close();
    }
}
