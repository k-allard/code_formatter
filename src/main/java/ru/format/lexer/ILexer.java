package ru.format.lexer;

import ru.format.exceptions.ReaderException;

import java.lang.reflect.InvocationTargetException;

public interface ILexer {
    boolean hasMoreTokens() throws ReaderException;

    IToken nextToken() throws ReaderException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException;
}
