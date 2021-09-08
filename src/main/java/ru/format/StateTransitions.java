package ru.format;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class StateTransitions {

    private final List<State> stateTransitions;

    public StateTransitions(String filename) {
        Gson gson = new GsonBuilder().create();
        InputStream file = StateTransitions.class.getResourceAsStream(filename);
        if (file == null) {
            log.error("Error opening resource " + filename);
        }
        assert file != null;
        State[] stateTransitionsArray = gson.fromJson(
                new InputStreamReader(file), State[].class);
        stateTransitions = Arrays.stream(stateTransitionsArray)
                .collect(Collectors.toList());
    }

    private Action findActionByStateAndInput(String state, String input) {
        State currentState = stateTransitions.get(stateTransitions.indexOf(new State(state)));
        int indexOfAction = currentState.getActions().indexOf(new Action(input));
        if (indexOfAction == -1) {
            indexOfAction = currentState.getActions().indexOf(new Action(null));
        }
        return currentState.getActions().get(indexOfAction);
    }

    public String findCommandByStateAndInput(String state, String input) {
        return findActionByStateAndInput(state, input).getCommand();
    }

    public String findNewStateByStateAndInput(String state, String input) {
        return findActionByStateAndInput(state, input).getState();
    }
}
