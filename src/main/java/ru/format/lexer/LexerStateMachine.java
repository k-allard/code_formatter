package ru.format.lexer;

import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;

public class LexerStateMachine implements ILexer {

    private final IReader reader;

    public LexerStateMachine(IReader reader) throws ReaderException {
        this.reader = reader;
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChars();
    }

    @Override
    public IToken readToken() throws ReaderException {
        char ch;
        State state = State.INITIAL;
        StringBuilder stringBuilder = new StringBuilder();
        CommandRepository commandRepository = new CommandRepository();
        StateTransitions stateTransitions = new StateTransitions();

        while (reader.hasChars() && state != State.TERMINATED) {
            stringBuilder.append(ch = reader.readChar());
            commandRepository.getCommand(state, ch).execute();
            state = stateTransitions.nextState(state, ch);
        }
        return new Token(state.toString(), stringBuilder.toString());
    }
}
