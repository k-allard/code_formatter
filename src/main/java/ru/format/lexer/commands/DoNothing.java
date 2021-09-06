package ru.format.lexer.commands;

import ru.format.exceptions.WriterException;
import ru.format.lexer.ICommand;
import ru.format.lexer.IContext;
import ru.format.lexer.IToken;

public class DoNothing implements ICommand {

    public void execute(char ch, IContext context) {
    }
}
