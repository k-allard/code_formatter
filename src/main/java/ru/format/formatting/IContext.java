package ru.format.formatting;

import ru.format.exceptions.WriterException;
import ru.format.lexer.IToken;

public interface IContext {
    void writeLexeme(IToken token) throws WriterException;

    void writeNewLine() throws WriterException;

    void writeIndent() throws WriterException;

    void incrementIndent();

    void decrementIndent();

    void writeSpace() throws WriterException;
}
