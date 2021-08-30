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
        char ch;
        State state = State.INITIAL;
        StringBuilder stringBuilder = new StringBuilder();


        while (reader.hasChars() && state != State.TERMINATED) {
            stringBuilder.append(ch = reader.readChar());
            commandRepository.getCommand(state, ch).execute();
            state = stateTransitions.nextState(state, ch);
        }
        return new Token(state.toString(), stringBuilder.toString());
    }
}
