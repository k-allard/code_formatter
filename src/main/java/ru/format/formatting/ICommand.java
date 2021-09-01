package ru.format.formatting;

import ru.format.exceptions.WriterException;
import ru.format.lexer.IToken;

public interface ICommand {
    void execute(IToken token, IContext context) throws WriterException;
}
