package ru.format.formatting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class StateTransitions {

    private final FormattingState[] states;

    public StateTransitions() {
        Gson gson = new GsonBuilder().create();
        try (InputStream file = ru.format.lexer.StateTransitions.class.getResourceAsStream("/FormatterStateTransitions.json")) {
            assert file != null;                                                   // TODO check if i need this assert
            states = gson.fromJson(new InputStreamReader(file), FormattingState[].class);
        } catch (IOException e) {
            log.debug("Error with file FormatterStateTransitions.json");
            throw new IllegalArgumentException();
        }
    }
}
