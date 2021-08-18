package ru.format;

import org.junit.jupiter.api.Test;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.formater.Formatter;
import ru.format.parser.StringReader;
import ru.format.parser.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterTest {

    @Test
    void formatTest() throws WriterException, ReaderException {
        var in = new StringReader("if (boo == null){ return 1;}\n" +
                "else if (boo == 1)   {      return 2; }    \n" +
                "   else \n" +
                "{ if (boo > 10) \n" +
                "   {\n" +
                "boo -= 1;    \n" +
                "return boo;\n" +
                " }  \n" +
                "      return 4;\n" +
                "} ");
        var out = new StringWriter();
        Formatter formatter = new Formatter();
        formatter.format(in, out);

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
                "}\n"

                , out.toString());
    }
}
