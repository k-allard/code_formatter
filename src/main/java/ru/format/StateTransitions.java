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
}
