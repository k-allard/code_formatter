package ru.format.formatting;

import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.IWriter;
import ru.format.lexer.ILexer;
import ru.format.lexer.IToken;

import java.lang.reflect.InvocationTargetException;


@Slf4j
public class FormatterStateMachine implements IFormatter {

    private final CommandRepository commandRepository;
    private final StateTransitions stateTransitions;
    private final IContext context;

    public FormatterStateMachine(IWriter writer) {
        context = new Context(writer);
        commandRepository = new CommandRepository();
        stateTransitions = new StateTransitions();
    }

    @Override
    public void format(ILexer lexer) throws FormatterException, ReaderException, WriterException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        FormatterState state = null;
        while (lexer.hasMoreTokens()) {
            state = (state == FormatterState.TERMINATED)
                    ? FormatterState.INITIAL
                    : FormatterState.NEW_LINE_START;
            while (state != FormatterState.TERMINATED) {
                IToken token = lexer.nextToken();
                ICommand command = commandRepository.getCommand(state, token);
                command.execute(token, context);
                FormatterState newState = stateTransitions.nextState(state, token);
                log.debug("     [{}]->[{}]-[{}]->[{}]", state, token.getName(), token.getLexeme(), newState);
                log.debug("     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                state = newState;
            }
        }
    }
}
