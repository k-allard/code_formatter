package ru.format.lexer.commands;

import ru.format.lexer.ICommand;
import ru.format.lexer.IContext;

public class AppendLexeme implements ICommand {

    public void execute(char ch, IContext context) {
        context.appendLexeme(ch);
    }
}
