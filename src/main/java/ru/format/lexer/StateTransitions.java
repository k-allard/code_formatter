package ru.format.lexer;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class StateTransitions {
    private Signal signal;
    private State state;
    private final Map<Pair<State, Signal>, State> stateTransitionMap;

    public StateTransitions() {
        stateTransitionMap = new HashMap<>();
        stateTransitionMap.put(new ImmutablePair<>(State.INITIAL, new Signal(' ')), State.SPACING);

    }

    State nextState(State state, Signal signal) {
        return stateTransitionMap.getOrDefault(new ImmutablePair<>(state, signal), State.TERMINATED);
    }
}
