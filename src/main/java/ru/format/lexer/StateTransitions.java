package ru.format.lexer;

import java.util.HashMap;
import java.util.Map;
import ru.format.Pair;

public class StateTransitions {
    private LexerState state;
    private final Map<Pair<LexerState, Character>, LexerState> stateTransitionMap;

    public StateTransitions() {
        stateTransitionMap = new HashMap<>();
        stateTransitionMap.put(Pair.create(LexerState.INITIAL, ' '), LexerState.SPACING);
        stateTransitionMap.put(Pair.create(LexerState.INITIAL, '\r'), LexerState.INITIAL);
        stateTransitionMap.put(Pair.create(LexerState.SPACING, '\r'), LexerState.SPACING);

        stateTransitionMap.put(Pair.create(LexerState.SPACING, ' '), LexerState.SPACING);
        stateTransitionMap.put(Pair.create(LexerState.SPACING, null), LexerState.TERMINATED);
        stateTransitionMap.put(Pair.create(LexerState.INITIAL, null), LexerState.TERMINATED);
    }

    LexerState nextState(LexerState state, Character ch) {
        LexerState newState = stateTransitionMap.get(Pair.create(state, ch));
        return (newState == null)
                ? stateTransitionMap.get(Pair.create(state, null))
                : newState;
    }
}
