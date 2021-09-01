package ru.format.formatting;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import ru.format.Pair;
import ru.format.formatting.commands.Char;
import ru.format.formatting.commands.CloseCurlyBracket;
import ru.format.formatting.commands.OpenCurlyBracket;
import ru.format.formatting.commands.Semicolon;
import ru.format.lexer.IToken;

@Slf4j
public class CommandRepository {

    private final Map<Pair<FormatterState, String>, ICommand> commandMap;

    public CommandRepository() {
        commandMap = new HashMap<>();
        commandMap.put(Pair.create(FormatterState.INITIAL, "open"), new OpenCurlyBracket());
        commandMap.put(Pair.create(FormatterState.INITIAL, "char"), new Char());
        commandMap.put(Pair.create(FormatterState.INITIAL, "close"), new CloseCurlyBracket());
        commandMap.put(Pair.create(FormatterState.INITIAL, "semicolon"), new Semicolon());
    }

    public ICommand getCommand(FormatterState state, IToken token) {
        return commandMap.get(Pair.create(state, token.getName()));
    }

}
