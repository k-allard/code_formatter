package ru.format.formatting.commands;

import ru.format.exceptions.WriterException;
import ru.format.formatting.ICommand;
import ru.format.formatting.IContext;
import ru.format.lexer.IToken;

public class OpenCurlyBracketWithSpace implements ICommand {

    @Override
    public void execute(IToken token, IContext context) throws WriterException {
        context.writeSpace();
        context.writeLexeme(token);
        context.writeNewLine();
        context.incrementIndent();
    }
}
