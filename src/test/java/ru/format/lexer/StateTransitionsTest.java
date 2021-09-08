package ru.format.lexer;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.format.Action;
import ru.format.State;
import ru.format.StateTransitions;

class StateTransitionsTest {
    private static final String JSON_FOR_LEXER = "/LexerStateTransitions.json";

    @Test
    void print_ParsedLexerStateTransitions_json() {
        StateTransitions stateTransitions = new StateTransitions(JSON_FOR_LEXER);

        List<State> states = stateTransitions.getStateTransitions();
        for (State state : states) {
            System.out.println("State " + state.getState() + ":");
            ArrayList<Action> actions = state.getActions();
            for (Action action : actions) {
                System.out.print("      [");
                if (action.getInput() != null && action.getInput().equals("\n")) {
                    System.out.print("\\n");
                }
                else if (action.getInput() != null && action.getInput().equals("\r")) {
                    System.out.print("\\r");
                }
                else {
                    System.out.print(action.getInput());
                }
                System.out.print("]-[" + action.getCommand() + "]-[" + action.getState() + "]\n");
            }
            System.out.println("__________________________");
        }
    }

    @Test
    void find_something_from_parsed_json() {
        StateTransitions stateTransitions = new StateTransitions(JSON_FOR_LEXER);

        List<State> statesList = stateTransitions.getStateTransitions();
        State stateThatIFound = statesList.get(statesList.indexOf(new State("SPACING")));
        Action actionThatIFound = stateThatIFound.getActions().get(stateThatIFound.getActions().indexOf(new Action("\r")));
        System.out.println(actionThatIFound);
    }
}