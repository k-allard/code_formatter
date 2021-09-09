package ru.format.lexer;

import java.lang.reflect.InvocationTargetException;
import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.ReflectionException;
import ru.format.io.IReader;
import ru.format.io.PostponeReader;
import ru.format.statetransitions.StateTransitions;

@Slf4j
public class LexerStateMachine implements ILexer {

    private final IReader reader;
    private final IReader postponeReader;
    private final IContext context;
    private final StateTransitions stateTransitions;
    private static final String COMMAND_PACKAGE = "ru.format.lexer.commands";
    private static final String JSON_FOR_LEXER = "/LexerStateTransitions.json";

    public LexerStateMachine(IReader reader) {
        log.debug("New Lexer State Machine created");
        this.reader = reader;
        stateTransitions = new StateTransitions(JSON_FOR_LEXER);
        StringBuilder postponeString = new StringBuilder();
        postponeReader = new PostponeReader(postponeString);
        context = new Context(postponeString);
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars();
    }

    @Override
    public IToken nextToken() throws ReaderException, IllegalAccessException {
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

    private ICommand createCommand(final String className) {
        String fullName = COMMAND_PACKAGE + "." + className;
        try {
            return (ICommand) Class.forName(fullName).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException
                | InvocationTargetException | InstantiationException
                | IllegalAccessException e) {
            log.error("Reflection error. Error while instantiate a class via its String name: " + fullName, e);
            throw new ReflectionException("Error while instantiate a class via its String name: " + fullName);
        }

    }

    private String step(String state, IReader reader) throws ReaderException {
        char ch = reader.readChar();
        ICommand command = createCommand(stateTransitions.findCommandByStateAndInput(state, "" + ch));
        command.execute(ch, context);
        String newState = stateTransitions.findNewStateByStateAndInput(state, "" + ch);
        log.debug("              [{}]->[{}]->[{}]", state, ch, newState);
        return newState;
    }
}
