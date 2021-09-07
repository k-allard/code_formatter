package ru.format.lexer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class StateTransitionsTest {
    @Test
    void tes1() {
        StateTransitions stateTransitions = new StateTransitions();

        LexerState[] states = stateTransitions.getStates();
        for (LexerState state : states) {
            System.out.println("State " + state.getState() + ":");
            ArrayList<Action> actions = state.getActions();
            for (Action action : actions) {
                System.out.print("      [");
                if (action.input != null && action.input.equals("\n")) {
                    System.out.print("\\n");
                }
                else if (action.input != null && action.input.equals("\r")) {
                    System.out.print("\\r");
                }
                else {
                    System.out.print(action.input);
                }
                System.out.print("]-[" + action.command + "]-[" + action.state + "]\n");
            }
            System.out.println("__________________________");
        }
    }
}