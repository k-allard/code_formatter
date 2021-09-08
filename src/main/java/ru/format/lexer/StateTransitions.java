package ru.format.lexer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class StateTransitions {

    private final LexerState[] states;
    private static final String JSON_FOR_LEXER = "/LexerStateTransitions.json";

    public StateTransitions() {
        Gson gson = new GsonBuilder().create();
        InputStream file = StateTransitions.class.getResourceAsStream(JSON_FOR_LEXER);
        if (file == null) {
            log.error("Error opening resource " + JSON_FOR_LEXER);
        }
        assert file != null;
        states = gson.fromJson(new InputStreamReader(file), LexerState[].class);
    }
}
