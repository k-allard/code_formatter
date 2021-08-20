package ru.format.formatting;

import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.IReader;
import ru.format.io.IWriter;

public interface IFormatter {

    void format(IReader in, IWriter out) throws FormatterException, ReaderException, WriterException;
}
