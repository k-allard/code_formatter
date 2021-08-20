package ru.format.exceptions;

import java.io.IOException;

public class WriterException extends IOException {

    public WriterException(String msg) {
        super(msg);
    }
}
