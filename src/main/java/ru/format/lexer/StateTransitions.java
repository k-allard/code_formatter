package ru.format.lexer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.format.Pair;

@Slf4j
@Getter
public class StateTransitions {
    LexerState[] states;

    //    private final Map<Pair<LexerState, Character>, LexerState> stateTransitionMap;

    public StateTransitions() {
//        Gson gson = new Gson();
//        try (InputStream file = StateTransitions.class.
//                getResourceAsStream("/LexerStateTransitions.json")) {
//            assert file != null;
//            JsonArray states = gson.fromJson(
//                    new InputStreamReader(file), JsonArray.class);
//            for (JsonElement state : states) {
//                JsonObject stateObject = state.getAsJsonObject();
//                String stateName = stateObject.get("state").getAsString();
//                JsonArray actions = stateObject.getAsJsonArray("actions");
//                for (JsonElement action : actions) {
//                    // ...
//                }
//            }
//        }

        Gson gson = new GsonBuilder().create();
        try (InputStream file = StateTransitions.class.getResourceAsStream("/LexerStateTransitions.json")) {
            assert file != null;            // TODO check if i need this assert
            states = gson.fromJson(new InputStreamReader(file), LexerState[].class);
        } catch (IOException e) {
            log.debug("Error with file LexerStateTransitions.json");
            throw new IllegalArgumentException();
        }

//        stateTransitionMap = new HashMap<>();

    }

//    String nextState(Character ch) {
//        LexerState newState = stateTransitionMap.get(Pair.create(state, ch));
//        return (newState == null)
//                ? stateTransitionMap.get(Pair.create(state, null))
//                : newState;
//    }

}
