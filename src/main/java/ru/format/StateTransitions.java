package ru.format;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.format.State;

@Slf4j
@Getter
public class StateTransitions {

    private final State[] stateTransitions;

    public StateTransitions(String filename) {
        Gson gson = new GsonBuilder().create();
        InputStream file = StateTransitions.class.getResourceAsStream(filename);
        if (file == null) {
            log.error("Error opening resource " + filename);
        }
        assert file != null;
        stateTransitions = gson.fromJson(new InputStreamReader(file), State[].class);
    }
}
