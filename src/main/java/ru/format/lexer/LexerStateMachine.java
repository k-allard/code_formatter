package ru.format.lexer;

import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;
import ru.format.io.PostponeReader;

import java.io.IOException;

@Slf4j
public class LexerStateMachine implements ILexer {

    private final StateTransitions stateTransitions;
    private final IReader reader;
    private final IReader postponeReader;
    private TokenBuilder tokenBuilder;
    private final IContext context;

    public LexerStateMachine(IReader reader) {
        log.debug("New Lexer State Machine created");
        this.reader = reader;
        stateTransitions = new StateTransitions();
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
        while (postponeReader.hasChars() && state.equals("TERMINATED")) {     //TODO
            log.debug("*reading postpone*");
            state = step(state, postponeReader);
        }
        postponeReader.clearBuffer();
        while (reader.hasChars() && state.equals("TERMINATED")) {        //TODO
            state = step(state, reader);
        }
        return context.getTokenBuilder().buildToken();
    }

    private String step(String state, IReader reader) throws ReaderException {
        char ch = reader.readChar();
        ICommand command = null; //TODO
        command.execute(ch, context);
        String newState = null; //TODO
        log.debug("              [{}]->[{}]->[{}]", state, ch, newState);
        return newState;
    }
}
