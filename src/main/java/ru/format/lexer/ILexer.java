package ru.format.lexer;

import java.lang.reflect.InvocationTargetException;
import ru.format.exceptions.ReaderException;

public interface ILexer {
    boolean hasMoreTokens() throws ReaderException;

    IToken nextToken() throws ReaderException, IllegalAccessException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException;
}
