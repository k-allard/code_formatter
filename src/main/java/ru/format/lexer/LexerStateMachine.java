package ru.format.lexer;

import ru.format.exceptions.CloseException;
import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;
import ru.format.io.StringReader;

public class LexerStateMachine implements ILexer {

    private final CommandRepository commandRepository;
    private final StateTransitions stateTransitions;
    private final IReader reader;
    private final IReader postponeReader;
    private final TokenBuilder tokenBuilder;
    private final Command command;
    private final IContext context;

    public LexerStateMachine(IReader reader) throws ReaderException {
        this.reader = reader;
        commandRepository = new CommandRepository();
        stateTransitions = new StateTransitions();
        postponeReader = new PostponeReader();
        tokenBuilder = new TokenBuilder();
        command = new Command();
        context = new Context();
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars();
    }

    @Override
    public IToken nextToken() throws ReaderException {
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
        commandRepository.getCommand(state, ch).execute(ch, context);
        return stateTransitions.nextState(state, ch);
    }
}
