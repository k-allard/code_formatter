package ru.format.lexer;

import org.junit.jupiter.api.Test;
import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;
import ru.format.io.StringReader;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LexerStateMachineTest {

    @Test
    public void lexerTest1() throws ReaderException, IllegalAccessException {
        IReader in = new StringReader("     if ()\n{  r;}");
        ILexer lexer = new LexerStateMachine(in);
        IToken token = lexer.nextToken();
        assertEquals("spaces", token.getName());
        assertEquals("     ", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("char", token.getName());
        assertEquals("i", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("char", token.getName());
        assertEquals("f", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("space", token.getName());
        assertEquals(" ", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("char", token.getName());
        assertEquals("(", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("char", token.getName());
        assertEquals(")", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("newline", token.getName());
        assertEquals("\n", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("open", token.getName());
        assertEquals("{", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("spaces", token.getName());
        assertEquals("  ", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("char", token.getName());
        assertEquals("r", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("semicolon", token.getName());
        assertEquals(";", token.getLexeme());

        token = lexer.nextToken();
        assertEquals("close", token.getName());
        assertEquals("}", token.getLexeme());
    }
}
