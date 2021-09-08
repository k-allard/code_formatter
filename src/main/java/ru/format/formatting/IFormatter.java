package ru.format.formatting;

import java.lang.reflect.InvocationTargetException;
import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.lexer.ILexer;

public interface IFormatter {
    void format(ILexer lexer) throws FormatterException, ReaderException, WriterException,
            IllegalAccessException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException;
}
