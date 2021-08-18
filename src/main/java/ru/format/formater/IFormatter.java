package ru.format.formater;

import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.parser.IReader;
import ru.format.parser.IWriter;

public interface IFormatter {

    void format(IReader in, IWriter out) throws FormatterException, ReaderException, WriterException;
}
