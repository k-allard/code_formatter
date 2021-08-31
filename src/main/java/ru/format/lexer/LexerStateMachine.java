package ru.format.lexer;

import java.util.HashMap;
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

    private final HashMap<Character, String> tokenNames;

    public LexerStateMachine(IReader reader) {
        log.debug("New Lexer State Machine created");
        this.reader = reader;
        commandRepository = new CommandRepository();
        stateTransitions = new StateTransitions();
        postponeString = new StringBuilder();
        postponeReader = new PostponeReader(postponeString);

        tokenNames = new HashMap<>();
        tokenNames.put('\n', "newline");
        tokenNames.put(';', "semicolon");
        tokenNames.put('{', "open");
        tokenNames.put('}', "close");

    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars();
    }

    @Override
    public IToken nextToken() throws ReaderException {
        tokenBuilder = new TokenBuilder();
        State state = State.INITIAL;
        while (postponeReader.hasChars() && state != State.TERMINATED) {
            state = step(state, postponeReader);
        }

        while (reader.hasChars() && state != State.TERMINATED) {
            state = step(state, reader);
        }
        return tokenBuilder.buildToken();
    }

    private State step(State state, IReader reader) throws ReaderException {
        char ch = reader.readChar();
        Command command = commandRepository.getCommand(state, new Signal(ch));
        if (command == null) {
            log.debug("FAIL: Command for state [{}] and ch [{}] not found", state, ch);
        } else {
            command.execute(new Signal(ch), this);
            log.debug("SUCCESS: Command [{}] for state [{}] and ch [{}] executed", command.getCommandType(), state, ch);
        }
        State newState = stateTransitions.nextState(state, new Signal(ch));
        log.debug("State transition: [{}] ---> [{}]", state, newState);
        if (state == State.SPACING) {
            setTokenName("space");
        } else {
            setTokenName(tokenNames.getOrDefault(ch, "char"));
        }
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
