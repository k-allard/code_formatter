package ru.format.lexer.commands;

import ru.format.lexer.ICommand;
import ru.format.lexer.IContext;

public class ForReady implements ICommand {

    public void execute(char ch, IContext context) {
        context.setTokenName("for");
        context.appendLexeme(ch);
    }
}
