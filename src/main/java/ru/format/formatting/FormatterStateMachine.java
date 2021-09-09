package ru.format.formatting;

import java.lang.reflect.InvocationTargetException;
import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.*;
import ru.format.io.IWriter;
import ru.format.lexer.ILexer;
import ru.format.lexer.IToken;
import ru.format.statetransitions.StateTransitions;

@Slf4j
public class FormatterStateMachine implements IFormatter {

    private final IContext context;
    private final StateTransitions stateTransitions;
    private static final String COMMAND_PACKAGE = "ru.format.formatting.commands.";
    private static final String JSON_FOR_FORMATTER = "/FormatterStateTransitions.json";

    public FormatterStateMachine(IWriter writer) {
        log.debug("New Formatter State Machine created");
        context = new Context(writer);
        stateTransitions = new StateTransitions(JSON_FOR_FORMATTER);
    }

    private ICommand createCommand(final String className) {
        String fullName = COMMAND_PACKAGE + className;
        try {
            return (ICommand) Class.forName(fullName).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException
                | InvocationTargetException | InstantiationException
                | IllegalAccessException e) {
            log.error("Reflection error. Error while instantiate a class via its String name: " + fullName, e);
            throw new ReflectionException("Error while instantiate a class via its String name: " + fullName);
        }
    }

    @Override
    public void format(ILexer lexer) throws FormatterException, ReaderException,
            WriterException, IllegalAccessException {
        String state = null;
        while (lexer.hasMoreTokens()) {
            state = (state != null && state.equals("TERMINATED"))
                    ? "INITIAL"
                    : "NEW_LINE_START";
            while (!state.equals("TERMINATED")) {
                IToken token = lexer.nextToken();
                ICommand command = createCommand(stateTransitions.findCommandByStateAndInput(state, token.getName()));
                command.execute(token, context);
                String newState = stateTransitions.findNewStateByStateAndInput(state, token.getName());
                log.debug("     [{}]->[{}]-[{}]->[{}]", state, token.getName(), token.getLexeme(), newState);
                log.debug("     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                state = newState;
            }
        }
    }
}
