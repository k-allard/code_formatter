package ru.format.lexer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Action {
    String input;
    String command;
    String state;

    @Override
    public String toString() {
        return "Action: input ["
                + input
                + "], command ["
                + command
                + "], state ["
                + state
                + "]";
    }
}
