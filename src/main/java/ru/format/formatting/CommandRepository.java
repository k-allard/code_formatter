package ru.format.formatting;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import ru.format.Pair;
import ru.format.formatting.commands.*;
import ru.format.lexer.IToken;

@Slf4j
public class CommandRepository {

    private final Map<Pair<FormatterState, String>, ICommand> commandMap;

    public CommandRepository() {
        commandMap = new HashMap<>();

        commandMap.put(Pair.create(FormatterState.INITIAL, "open"), new OpenCurlyBracket());
        commandMap.put(Pair.create(FormatterState.INITIAL, "char"), new WriteLexeme());
        commandMap.put(Pair.create(FormatterState.INITIAL, "close"), new CloseCurlyBracket());
        commandMap.put(Pair.create(FormatterState.INITIAL, "semicolon"), new Semicolon());
        commandMap.put(Pair.create(FormatterState.INITIAL, "space"), new Space());
        commandMap.put(Pair.create(FormatterState.INITIAL, "spaces"), new Space());
        commandMap.put(Pair.create(FormatterState.INITIAL, "newline"), new DoNothing());

        commandMap.put(Pair.create(FormatterState.NEW_LINE_START, "close"), new CloseCurlyBracket());
        commandMap.put(Pair.create(FormatterState.NEW_LINE_START, "open"), new WriteIndentAndLexeme());
        commandMap.put(Pair.create(FormatterState.NEW_LINE_START, "space"), new DoNothing());
        commandMap.put(Pair.create(FormatterState.NEW_LINE_START, "spaces"), new DoNothing());
        commandMap.put(Pair.create(FormatterState.NEW_LINE_START, null), new WriteIndentAndLexeme());
    }

    public ICommand getCommand(FormatterState state, IToken token) {
        ICommand newCommand = commandMap.get(Pair.create(state, token.getName()));

        return (newCommand == null)
                ? commandMap.get(Pair.create(state, null))
                : newCommand;
    }

}
