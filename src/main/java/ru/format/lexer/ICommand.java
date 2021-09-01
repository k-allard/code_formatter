package ru.format.lexer;

import ru.format.lexer.IContext;

public interface ICommand {
    void execute(char ch, IContext context);
}
