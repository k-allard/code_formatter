package ru.format.formatting;

import org.junit.jupiter.api.Test;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.IReader;
import ru.format.io.IWriter;
import ru.format.io.StringReader;
import ru.format.io.StringWriter;
import ru.format.lexer.ILexer;
import ru.format.lexer.LexerStateMachine;
import ru.format.formatting.FormatterStateMachine;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterStateMachineTest {

    @Test
    void formatterTest1() throws WriterException, ReaderException, IllegalAccessException {
        IReader in = new StringReader("     if ()\n{  r;}");
        ILexer lexer = new LexerStateMachine(in);
        IWriter writer = new StringWriter();
        IFormatter formatter = new FormatterStateMachine(writer);
        formatter.format(lexer);
        System.out.print("[");
        System.out.print(writer);
        System.out.println("]");

        assertEquals("if () {\n" +
                "    r;\n" +
                "}", writer.toString());
    }
}
