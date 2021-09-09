package ru.format.exceptions;

public class IncorrectFileException extends RuntimeException {

    public IncorrectFileException(String errorMessage) {
        super(errorMessage);
    }
}
