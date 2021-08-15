package ru.format;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputterTest {

    @Test
    void addSpacesZeroLevel() {
        assertEquals("", Outputter.addSpaces(0));
    }

    @Test
    void addSpacesFirstLevel() {
        assertEquals("    ", Outputter.addSpaces(1));
    }

    @Test
    void addSpacesFifthLevel() {
        assertEquals("                    ", Outputter.addSpaces(5));
    }
}
