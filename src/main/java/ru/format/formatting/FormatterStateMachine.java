package ru.format.formatting;

import lombok.extern.slf4j.Slf4j;
import ru.format.lexer.LexerState;
import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.IWriter;
import ru.format.lexer.ILexer;
import ru.format.lexer.IToken;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class FormatterStateMachine implements IFormatter {

    private final FormattingState[] states;
    private final IContext context;
    private static final String COMMAND_PACKAGE = "ru.format.formatting.commands";

    public FormatterStateMachine(IWriter writer) {
        log.debug("New Formatter State Machine created");
        context = new Context(writer);
        StateTransitions stateTransitions = new StateTransitions();
        states = stateTransitions.getStates();
    }

    private ICommand createCommand(final String className) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        String fullName = COMMAND_PACKAGE + "." + className;
        return (ICommand) Class.forName(fullName).getDeclaredConstructor().newInstance();
    }

    private FormattingAction findActionByStateAndInput(String state, IToken token) {
        List<FormattingState> statesList = Arrays.stream(states).collect(Collectors.toList());
        FormattingState currentState = statesList.get(statesList.indexOf(new FormattingState(state)));
        int indexOfAction = currentState.getActions().indexOf(new FormattingAction(token.getName()));
        if (indexOfAction == -1) {
            indexOfAction = currentState.getActions().indexOf(new FormattingAction(null));
        }
        return currentState.getActions().get(indexOfAction);
    }

    private String findCommandByStateAndInput(String state, IToken token) {
        return findActionByStateAndInput(state, token).getCommand();
    }

    private String findNewStateByStateAndInput(String state, IToken token) {
        return findActionByStateAndInput(state, token).getState();
    }

    @Override
    public void format(ILexer lexer) throws FormatterException, ReaderException, WriterException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        String state = null;
        while (lexer.hasMoreTokens()) {
            state = (state != null && state.equals("TERMINATED"))
                    ? "INITIAL"
                    : "NEW_LINE_START";
            while (!state.equals("TERMINATED")) {
                IToken token = lexer.nextToken();
                ICommand command = createCommand(findCommandByStateAndInput(state, token));
                command.execute(token, context);
                String newState = findNewStateByStateAndInput(state, token);
                log.debug("     [{}]->[{}]-[{}]->[{}]", state, token.getName(), token.getLexeme(), newState);
                log.debug("     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                state = newState;
            }
        }
    }
}
