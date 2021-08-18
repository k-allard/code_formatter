package ru.format.parser;

import ru.format.exceptions.WriterException;

public interface IWriter {

    void writeChar(char ch) throws WriterException;
}
