package ru.format.statetransitions;

import java.util.ArrayList;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateDTO {
    String state;
    ArrayList<Action> actions;

    public StateDTO(String state) {
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
        StateDTO state1 = (StateDTO) o;
        return state.equals(state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
