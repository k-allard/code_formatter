package ru.format.lexer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class StateTransitionsTest {
    @Test
    void print_PasrsedLexerStateTransitions_json() {
        StateTransitions stateTransitions = new StateTransitions();

        LexerState[] states = stateTransitions.getStates();
        for (LexerState state : states) {
            System.out.println("State " + state.getState() + ":");
            ArrayList<LexerAction> actions = state.getActions();
            for (LexerAction action : actions) {
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

    @Test
    void find_something_from_parsed_json() {
        StateTransitions stateTransitions = new StateTransitions();

        LexerState[] states = stateTransitions.getStates();
        List<LexerState> statesList = Arrays.stream(states).collect(Collectors.toList());
        LexerState stateThatIFound = statesList.get(statesList.indexOf(new LexerState("SPACING")));
        LexerAction actionThatIFound = stateThatIFound.getActions().get(stateThatIFound.getActions().indexOf(new LexerAction("\r")));
        System.out.println(actionThatIFound);
    }
}