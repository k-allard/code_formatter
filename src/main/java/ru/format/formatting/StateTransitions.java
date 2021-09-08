package ru.format.formatting;

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

    private final State[] states;
    private static final String JSON_FOR_FORMATTER = "/FormatterStateTransitions.json";

    public StateTransitions() {
        Gson gson = new GsonBuilder().create();
        InputStream file = ru.format.lexer.StateTransitions.class.getResourceAsStream(JSON_FOR_FORMATTER);
        if (file == null) {
            log.error("Error opening resource " + JSON_FOR_FORMATTER);
        }
        assert file != null;
        states = gson.fromJson(new InputStreamReader(file), State[].class);
    }
}
