package ru.format.statetransitions;

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
public class StateTransitions implements IStateTransitions {

    private final List<State> stateTransitions;

    public StateTransitions(String jsonInResources) {
        Gson gson = new GsonBuilder().create();
        InputStream file = StateTransitions.class.getResourceAsStream(jsonInResources);
        if (file == null) {
            log.error("Error opening resource " + jsonInResources);
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

    @Override
    public String findCommandByStateAndInput(String state, String input) {
        return findActionByStateAndInput(state, input).getCommand();
    }

    @Override
    public String findNewStateByStateAndInput(String state, String input) {
        return findActionByStateAndInput(state, input).getState();
    }
}
