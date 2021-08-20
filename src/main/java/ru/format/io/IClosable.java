package ru.format.io;

import ru.format.exceptions.CloseException;

public interface IClosable extends AutoCloseable {
    @Override
    void close() throws CloseException;
}
