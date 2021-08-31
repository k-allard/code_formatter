package ru.format.lexer;

public class Command {

    public CommandTypeEnum getCommandType() {
        return commandType;
    }

    private final CommandTypeEnum commandType;

    public Command(CommandTypeEnum commandType) {
        this.commandType = commandType;
    }

    public void execute(Signal signal, IContext context) {
        switch (commandType) {
            case CMD_APPEND_LEXEME:
                context.appendLexeme(signal.getCh());
                break;
            case CMD_APPEND_POSTPONE:
                context.appendPostpone(signal.getCh());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commandType);
        }
    }
}

