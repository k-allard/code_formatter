package ru.format.formatting;

import lombok.Getter;
import lombok.Setter;
import ru.format.lexer.IToken;

import java.util.Objects;

@Getter
@Setter
public class FormattingAction {
    public FormattingAction(String input) {
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
        FormattingAction action = (FormattingAction) o;
        return Objects.equals(input, action.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input);
    }
}
