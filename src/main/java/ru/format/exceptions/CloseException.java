package ru.format.exceptions;

import java.io.IOException;

public class CloseException extends IOException {

    public CloseException(String msg) {
        super(msg);
    }
}
