package ru.format.lexer;

public interface ICommand {
    void execute(char ch, IContext context);
}
