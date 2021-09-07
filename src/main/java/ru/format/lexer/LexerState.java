package ru.format.lexer;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
public class LexerState {
    public LexerState(String state) {
        this.state = state;
    }

    String state;
    ArrayList<Action> actions;

    @Override
    public String toString() {
        return "LexerState: "
                + state
//                + "  >>>>>"
//                + actions
//                + "<<<<<  "
                + "\n----------------\n"
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LexerState state1 = (LexerState) o;
        return state.equals(state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
