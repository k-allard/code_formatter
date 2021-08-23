package ru.format.formatting;

import java.util.List;
import ru.format.exceptions.ReaderException;

public interface ILexer {
    List<Lexeme> getLexemes() throws ReaderException;
}
