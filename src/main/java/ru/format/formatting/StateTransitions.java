package ru.format.formatting;

import java.util.HashMap;
import java.util.Map;
import ru.format.Pair;
import ru.format.lexer.IToken;

public class StateTransitions {
    private FormatterState state;
    private final Map<Pair<FormatterState, String>, FormatterState> stateTransitionMap;

    public StateTransitions() {
        stateTransitionMap = new HashMap<>();
        stateTransitionMap.put(Pair.create(FormatterState.INITIAL, "semicolon"), FormatterState.NEW_LINE_START);
        stateTransitionMap.put(Pair.create(FormatterState.NEW_LINE_START, "close"), FormatterState.NEW_LINE_START);
        stateTransitionMap.put(Pair.create(FormatterState.NEW_LINE_START, "space"), FormatterState.NEW_LINE_START);
        stateTransitionMap.put(Pair.create(FormatterState.NEW_LINE_START, "spaces"), FormatterState.NEW_LINE_START);
        stateTransitionMap.put(Pair.create(FormatterState.NEW_LINE_START, null), FormatterState.TERMINATED);
        stateTransitionMap.put(Pair.create(FormatterState.INITIAL, "newline"), FormatterState.TERMINATED);
        stateTransitionMap.put(Pair.create(FormatterState.INITIAL, "open"), FormatterState.NEW_LINE_START);
        stateTransitionMap.put(Pair.create(FormatterState.INITIAL, "char"), FormatterState.TERMINATED);
        stateTransitionMap.put(Pair.create(FormatterState.INITIAL, "close"), FormatterState.TERMINATED);
        stateTransitionMap.put(Pair.create(FormatterState.INITIAL, "space"), FormatterState.TERMINATED);
        stateTransitionMap.put(Pair.create(FormatterState.INITIAL, "spaces"), FormatterState.TERMINATED);
    }

    FormatterState nextState(FormatterState state, IToken token) {
        FormatterState newState = stateTransitionMap.get(Pair.create(state, token.getName()));
        return (newState == null)
                ? stateTransitionMap.get(Pair.create(state, null))
                : newState;
    }
}
