package ru.format.lexer.commands;

import ru.format.lexer.ICommand;
import ru.format.lexer.IContext;

public class NewLine implements ICommand {

    public void execute(char ch, IContext context) {
        context.appendLexeme(ch);
        context.setTokenName("newline");
    }

}

