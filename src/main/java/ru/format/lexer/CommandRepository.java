package ru.format.lexer;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import ru.format.Pair;
import ru.format.lexer.commands.*;

@Slf4j
public class CommandRepository {

    private final Map<Pair<LexerState, Character>, ICommand> commandMap;

    public CommandRepository() {
        commandMap = new HashMap<>();
        commandMap.put(Pair.create(LexerState.INITIAL, ' '), new Space());
        commandMap.put(Pair.create(LexerState.INITIAL, ';'), new Semicolon());
        commandMap.put(Pair.create(LexerState.INITIAL, '{'), new OpenCurlyBracket());
        commandMap.put(Pair.create(LexerState.INITIAL, '}'), new CloseCurlyBracket());
        commandMap.put(Pair.create(LexerState.INITIAL, '\n'), new NewLine());
        commandMap.put(Pair.create(LexerState.INITIAL, null), new Char());
        commandMap.put(Pair.create(LexerState.INITIAL, 'f'), new AppendLexeme());

        commandMap.put(Pair.create(LexerState.SPACING, ' '), new Spaces());
        commandMap.put(Pair.create(LexerState.SPACING, null), new AppendPostpone());
        commandMap.put(Pair.create(LexerState.SPACING, 'f'), new AppendLexeme());

        commandMap.put(Pair.create(LexerState.FOR1, 'o'), new AppendLexeme());
        commandMap.put(Pair.create(LexerState.FOR1, null), new AppendLexeme());
        commandMap.put(Pair.create(LexerState.FOR2, 'r'), new AppendLexeme());
        commandMap.put(Pair.create(LexerState.FOR2, null), new AppendLexeme());
        commandMap.put(Pair.create(LexerState.FOR3, null), new AppendLexeme());
        commandMap.put(Pair.create(LexerState.FOR3, ' '), new AppendLexeme());
        commandMap.put(Pair.create(LexerState.FOR3, '('), new AppendLexeme());
        commandMap.put(Pair.create(LexerState.FOR_START, null), new AppendLexeme());

    }

    public ICommand getCommand(LexerState state, Character ch) {
        ICommand command = commandMap.get(Pair.create(state, ch));
        return (command == null)
                ? commandMap.get(Pair.create(state, null))
                : command;
    }
}
