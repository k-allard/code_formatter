package ru.format.statetransitions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.format.Pair;
import ru.format.exceptions.IncorrectFileException;

@Slf4j
@Getter
public class StateTransitions implements IStateTransitions {

    private final Map<Pair<String, Character>, String> stateTransitions = null;
    private final Map<Pair<String, Character>, String> commands = null;


    public StateTransitions(String jsonInResources) {
        Gson gson = new GsonBuilder().create();
        InputStream file = StateTransitions.class.getResourceAsStream(jsonInResources);
        if (file == null) {
            log.error("Error opening resource " + jsonInResources);
            throw new IncorrectFileException("Error opening resource " + jsonInResources);
        }
        StateDTO[] stateTransitionsArray = gson.fromJson(
                new InputStreamReader(file), StateDTO[].class);

        for (StateDTO state : stateTransitionsArray) {
            String currentState = state.getState();
            for (Action action : state.getActions()) {
                stateTransitions.put(new Pair<>(currentState, action.input), action.getState());
                commands.put(new Pair<>(currentState, action.input), action.getCommand());
            }
        }
    }

//    private Action findActionByStateAndInput(String state, String input) {
//        StateDTO currentState = stateTransitions.get(stateTransitions.indexOf(new StateDTO(state)));
//        int indexOfAction = currentState.getActions().indexOf(new Action(input));
//        if (indexOfAction == -1) {
//            indexOfAction = currentState.getActions().indexOf(new Action(null));
//        }
//        return currentState.getActions().get(indexOfAction);
//    }
//
//    @Override
//    public String findCommandByStateAndInput(String state, String input) {
//        return findActionByStateAndInput(state, input).getCommand();
//    }
//
//    @Override
//    public String findNewStateByStateAndInput(String state, String input) {
//        return findActionByStateAndInput(state, input).getState();
//    }
}
