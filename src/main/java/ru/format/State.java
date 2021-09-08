package ru.format;

import java.util.ArrayList;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class State {
    String state;
    ArrayList<Action> actions;

    public State(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "State: "
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
        State state1 = (State) o;
        return state.equals(state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
