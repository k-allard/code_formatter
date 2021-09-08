package ru.format.statetransitions;

public interface IStateTransitions {
    String findCommandByStateAndInput(String state, String input);

    String findNewStateByStateAndInput(String state, String input);
}
