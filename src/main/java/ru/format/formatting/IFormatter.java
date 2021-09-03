package ru.format.formatting;

import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.IWriter;
import ru.format.lexer.ILexer;

public interface IFormatter {
    void format(ILexer lexer) throws FormatterException, ReaderException, WriterException, IllegalAccessException;
}
