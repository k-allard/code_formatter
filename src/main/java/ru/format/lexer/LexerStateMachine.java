package ru.format.lexer;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import ru.format.Action;
import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;
import ru.format.io.PostponeReader;

@Slf4j
public class LexerStateMachine implements ILexer {

    private final LexerState[] states;
    private final IReader reader;
    private final IReader postponeReader;
    private final IContext context;
    private static final String COMMAND_PACKAGE = "ru.format.lexer.commands";

    public LexerStateMachine(IReader reader) {
        log.debug("New Lexer State Machine created");
        this.reader = reader;
        StateTransitions stateTransitions = new StateTransitions();
        states = stateTransitions.getStates();
        StringBuilder postponeString = new StringBuilder();
        postponeReader = new PostponeReader(postponeString);
        context = new Context(postponeString);
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars();
    }

    @Override
    public IToken nextToken() throws ReaderException, IllegalAccessException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException {
        context.newTokenBuilder();
        String state = "INITIAL";
        while (postponeReader.hasChars() && !state.equals("TERMINATED")) {
            log.debug("*reading postpone*");
            state = step(state, postponeReader);            // TODO изменяем строку???
        }
        postponeReader.clearBuffer();
        while (reader.hasChars() && !state.equals("TERMINATED")) {
            state = step(state, reader);
        }
        return context.getTokenBuilder().buildToken();
    }

    private ICommand createCommand(final String className) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        String fullName = COMMAND_PACKAGE + "." + className;
        return (ICommand) Class.forName(fullName).getDeclaredConstructor().newInstance();
    }

    private Action findActionByStateAndInput(String state, String input) {
        List<LexerState> statesList = Arrays.stream(states).collect(Collectors.toList());
        LexerState currentState = statesList.get(statesList.indexOf(new LexerState(state)));
        int indexOfAction = currentState.getActions().indexOf(new Action(input));
        if (indexOfAction == -1) {
            indexOfAction = currentState.getActions().indexOf(new Action(null));
        }
        return currentState.getActions().get(indexOfAction);
    }

    private String findCommandByStateAndInput(String state, String input) {
        return findActionByStateAndInput(state, input).getCommand();
    }

    private String findNewStateByStateAndInput(String state, String input) {
        return findActionByStateAndInput(state, input).getState();
    }

    private String step(String state, IReader reader) throws ReaderException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        char ch = reader.readChar();
        ICommand command = createCommand(findCommandByStateAndInput(state, "" + ch));
        command.execute(ch, context);
        String newState = findNewStateByStateAndInput(state, "" + ch);
        log.debug("              [{}]->[{}]->[{}]", state, ch, newState);
        return newState;
    }
}
