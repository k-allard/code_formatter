package ru.format.lexer;

import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;
import ru.format.io.PostponeReader;

@Slf4j
public class LexerStateMachine implements ILexer, IContext {

    private final CommandRepository commandRepository;
    private final StateTransitions stateTransitions;
    private final IReader reader;
    private final IReader postponeReader;
    private TokenBuilder tokenBuilder;
    private final StringBuilder postponeString;

    public LexerStateMachine(IReader reader) {
        log.debug("New Lexer State Machine created");
        this.reader = reader;
        commandRepository = new CommandRepository();
        stateTransitions = new StateTransitions();
        postponeString = new StringBuilder();
        postponeReader = new PostponeReader(postponeString);
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars();
    }

    @Override
    public IToken nextToken() throws ReaderException, IllegalAccessException {
        tokenBuilder = new TokenBuilder();
        LexerState state = LexerState.INITIAL;
        while (postponeReader.hasChars() && state != LexerState.TERMINATED) {
            state = step(state, postponeReader);
        }
        postponeReader.clearBuffer();
        while (reader.hasChars() && state != LexerState.TERMINATED) {
            state = step(state, reader);
        }
        return tokenBuilder.buildToken();
    }

    private LexerState step(LexerState state, IReader reader) throws ReaderException {
        char ch = reader.readChar();
        ICommand command = commandRepository.getCommand(state, ch);
        command.execute(ch, this);
        LexerState newState = stateTransitions.nextState(state, ch);
        // log.debug("Char read: [{}]. State transition: [{}] ---> [{}]", ch, state, newState);
        return newState;
    }

    @Override
    public void appendLexeme(char ch) {
        tokenBuilder.appendLexeme(ch);
    }

    @Override
    public void setTokenName(String name) {
        tokenBuilder.setName(name);
    }

    @Override
    public void appendPostpone(char ch) {
        postponeString.append(ch);
    }
}
