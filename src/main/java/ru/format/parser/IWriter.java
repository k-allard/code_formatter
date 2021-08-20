package ru.format.parser;

import ru.format.exceptions.WriterException;

public interface IWriter extends IClosable {

    void writeChar(char ch) throws WriterException;
}
