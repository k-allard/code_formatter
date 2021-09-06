package ru.format.lexer;

import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;
import ru.format.io.PostponeReader;

@Slf4j
public class LexerStateMachine implements ILexer {

    private final CommandRepository commandRepository;
    private final StateTransitions stateTransitions;
    private final IReader reader;
    private final IReader postponeReader;
    private TokenBuilder tokenBuilder;
    private final IContext context;

    public LexerStateMachine(IReader reader) {
        log.debug("New Lexer State Machine created");
        this.reader = reader;
        commandRepository = new CommandRepository();
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
        LexerState state = LexerState.INITIAL;
        while (postponeReader.hasChars() && state != LexerState.TERMINATED) {
            log.debug("*reading postpone*");
            state = step(state, postponeReader);
        }
        postponeReader.clearBuffer();
        while (reader.hasChars() && state != LexerState.TERMINATED) {
            state = step(state, reader);
        }
        return context.getTokenBuilder().buildToken();
    }

    private LexerState step(LexerState state, IReader reader) throws ReaderException {
        char ch = reader.readChar();
        ICommand command = commandRepository.getCommand(state, ch);
        command.execute(ch, context);
        LexerState newState = stateTransitions.nextState(state, ch);
        log.debug("              [{}]->[{}]->[{}]", state, ch, newState);
        return newState;
    }
}
