package ru.format.lexer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class StateTransitions {

    private final LexerState[] states;

    public StateTransitions() {
        Gson gson = new GsonBuilder().create();
        try (InputStream file = StateTransitions.class.getResourceAsStream("/LexerStateTransitions.json")) {
            assert file != null;                                                   // TODO check if i need this assert
            states = gson.fromJson(new InputStreamReader(file), LexerState[].class);
        } catch (IOException e) {
            log.debug("Error with file LexerStateTransitions.json");
            throw new IllegalArgumentException();
        }
    }
}
