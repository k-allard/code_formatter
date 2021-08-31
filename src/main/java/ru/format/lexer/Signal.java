package ru.format.lexer;

import java.util.Objects;

public class Signal {

    private char ch;

    public Signal(char ch) {
        this.ch = ch;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Signal signal = (Signal) o;
        return ch == signal.ch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ch);
    }
}
