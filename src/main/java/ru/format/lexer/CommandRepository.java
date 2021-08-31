package ru.format.lexer;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@Slf4j
public class CommandRepository {

    private Map<Pair<State, Signal>, Command> commandMap;

    private Signal signal;
    private State state;
    private Command command;

    public CommandRepository() {
        commandMap = new HashMap<>();
        commandMap.put(new ImmutablePair<>(State.INITIAL, null), new Command(CommandTypeEnum.CMD_APPEND_LEXEME));
    }

    public Command getCommand(State state, Signal ch) {

        if (ch.getCh() != 'q') {
            ch = null;
        }
        return commandMap.getOrDefault(new ImmutablePair<>(state, ch), null);
    }
}
