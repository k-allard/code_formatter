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
        IReader in = new StringReader("     if  ()\n{  r;}");
        ILexer lexer = new LexerStateMachine(in);
        IWriter writer = new StringWriter();
        IFormatter formatter = new FormatterStateMachine(writer);
        formatter.format(lexer);
        System.out.print("[");
        System.out.print(writer);
        System.out.println("]");

        assertEquals("if () {\n" +
                "    r;\n" +
                "}\n", writer.toString());
    }

    @Test
    void formatterTest2() throws WriterException, ReaderException, IllegalAccessException {
        IReader in = new StringReader("if () {abc}");
        ILexer lexer = new LexerStateMachine(in);
        IWriter writer = new StringWriter();
        IFormatter formatter = new FormatterStateMachine(writer);
        formatter.format(lexer);
        System.out.print("[");
        System.out.print(writer);
        System.out.println("]");

        assertEquals("if () {\n    abc\n}\n", writer.toString());
    }

    @Test
    void formatterTest3() throws WriterException, ReaderException, IllegalAccessException {
        IReader in = new StringReader(
                "else \n" +
                "{ if (b) \n" +
                "   {\n" +
                "b = 1;    \n" +
                " }  \n" +
                "      ret 4;\n" +
                "} ");
        ILexer lexer = new LexerStateMachine(in);
        IWriter writer = new StringWriter();
        IFormatter formatter = new FormatterStateMachine(writer);
        formatter.format(lexer);
        System.out.print("[");
        System.out.print(writer);
        System.out.println("]");

        assertEquals("else {\n" +
                "    if (b) {\n" +
                "        b = 1;\n" +
                "    }\n" +
                "    ret 4;\n" +
                "}\n", writer.toString());
    }

    @Test
    void formatterTest4() throws WriterException, ReaderException, IllegalAccessException {
        IReader in = new StringReader("if (boo == null){ return 1;}\n" +
                "else if \n" +
                " (boo == 1)   {      return 2; }    \n" +
                "   else \n" +
                "{ if (boo > 10) \n" +
                "   {\n" +
                "boo -= 1;    \n" +
                "return boo;\n" +
                " }  \n" +
                "      return 4;\n" +
                "} ");
        ILexer lexer = new LexerStateMachine(in);
        IWriter writer = new StringWriter();
        IFormatter formatter = new FormatterStateMachine(writer);
        formatter.format(lexer);
        System.out.print("[");
        System.out.print(writer);
        System.out.println("]");

        assertEquals("if (boo == null) {\n" +
                "    return 1;\n" +
                "}\n" +
                "else if (boo == 1) {\n" +
                "    return 2;\n" +
                "}\n" +
                "else {\n" +
                "    if (boo > 10) {\n" +
                "        boo -= 1;\n" +
                "        return boo;\n" +
                "    }\n" +
                "    return 4;\n" +
                "}\n", writer.toString());
    }

    @Test
    void formatterTest5() throws WriterException, ReaderException, IllegalAccessException {
        IReader in = new StringReader("for (i = 0; i < 5; i++) {\n" +
                "  sout(i);\n" +
                "}\n");
        ILexer lexer = new LexerStateMachine(in);
        IWriter writer = new StringWriter();
        IFormatter formatter = new FormatterStateMachine(writer);
        formatter.format(lexer);
        System.out.print("[");
        System.out.print(writer);
        System.out.println("]");

        assertEquals("for (i = 0; i < 5; i++) {\n" +
                "    sout(i);\n" +
                "}\n", writer.toString());
    }
}

