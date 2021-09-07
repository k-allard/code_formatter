package ru.format.formatting;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
public class FormattingState {
    String state;
    ArrayList<FormattingAction> actions;

    public FormattingState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FormattingState: "
                + state
                + " >>>"
                + actions
                + "<<< "
                + "\n----------------\n"
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormattingState state1 = (FormattingState) o;
        return state.equals(state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
