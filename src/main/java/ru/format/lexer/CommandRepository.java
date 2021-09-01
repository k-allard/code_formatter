package ru.format.lexer;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import ru.format.Pair;
import ru.format.lexer.commands.AppendPostpone;
import ru.format.lexer.commands.Char;
import ru.format.lexer.commands.CloseCurlyBracket;
import ru.format.lexer.commands.NewLine;
import ru.format.lexer.commands.OpenCurlyBracket;
import ru.format.lexer.commands.Semicolon;
import ru.format.lexer.commands.Space;
import ru.format.lexer.commands.Spaces;

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
        commandMap.put(Pair.create(LexerState.SPACING, ' '), new Spaces());
        commandMap.put(Pair.create(LexerState.SPACING, null), new AppendPostpone());
    }

    public ICommand getCommand(LexerState state, Character ch) {
        ICommand command = commandMap.get(Pair.create(state, ch));
        return (command == null)
                ? commandMap.get(Pair.create(state, null))
                : command;
    }
}
