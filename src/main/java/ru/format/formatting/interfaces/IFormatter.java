package ru.format.formatting.interfaces;

import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.interfaces.IWriter;

public interface IFormatter {
    void format(ILexer lexer, IWriter writer) throws FormatterException, ReaderException, WriterException;
}
