package ru.format.lexer;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class LexerState {
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
}
