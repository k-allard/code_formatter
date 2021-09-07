package ru.format.lexer;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Action {
    public Action(String input) {
        this.input = input;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(input, action.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input);
    }
}
