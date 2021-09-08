package ru.format.lexer;

import java.util.ArrayList;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LexerState {
    String state;
    ArrayList<LexerAction> actions;

    public LexerState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LexerState: "
                + state
                + " >>>"
                + actions
                + "<<< "
                + "\n----------------\n"
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LexerState state1 = (LexerState) o;
        return state.equals(state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
