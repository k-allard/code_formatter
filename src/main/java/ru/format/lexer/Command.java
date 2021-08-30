package ru.format.lexer;

public class Command {

    private Context context;
    private Signal signal;

    public Command() {
        context = new Context();
    }

    public void execute(char ch, IContext context) {
    }
}
