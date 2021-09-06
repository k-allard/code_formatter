package ru.format.lexer.commands;

import ru.format.lexer.ICommand;
import ru.format.lexer.IContext;

public class ForBreakage implements ICommand {

    public void execute(char ch, IContext context) {
        context.setTokenName("char");
        context.appendPostpone(ch);
    }
}
