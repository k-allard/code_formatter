package ru.format.formatting;

import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.IWriter;
import ru.format.lexer.ILexer;
import ru.format.lexer.IToken;


@Slf4j
public class FormatterStateMachine implements IFormatter, IContext {

    private final CommandRepository commandRepository;
    private final StateTransitions stateTransitions;
    private static final int SPACES_FOR_LEVEL = 4;
    private final IWriter writer;
    private int indentLevel;

    public FormatterStateMachine(IWriter writer) {
        this.writer = writer;
        indentLevel = 0;
        commandRepository = new CommandRepository();
        stateTransitions = new StateTransitions();
    }

    @Override
    public void format(ILexer lexer, IWriter writer) throws FormatterException, ReaderException, WriterException, IllegalAccessException {
        FormatterState state = FormatterState.INITIAL;
        while (lexer.hasMoreTokens() && state != FormatterState.TERMINATED) {
            IToken token = lexer.nextToken();
            ICommand command = commandRepository.getCommand(state, token);
            command.execute(token, this);
            state = stateTransitions.nextState(state, token);
        }
    }

    /*
    // TODO: все методы ниже вынести в класс Context (implements IContext)
    */

    private void writeString(String str) throws WriterException {
        for (int i = 0; i < str.length(); i++) {
            writer.writeChar(str.charAt(i));
        }
    }

    @Override
    public void writeLexeme(IToken token) throws WriterException {
        writeString(token.getLexeme());
    }

    @Override
    public void writeNewLine() throws WriterException {
        writer.writeChar('\n');
    }

    @Override
    public void writeIndent() throws WriterException {
        writeString(" ".repeat(Math.max(0, indentLevel * SPACES_FOR_LEVEL)));
    }

    @Override
    public void incrementIndent() {
        indentLevel++;
    }

    @Override
    public void decrementIndent() {
        indentLevel--;
    }
}
