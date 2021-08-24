package ru.format.formatting;

import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.formatting.interfaces.IFormatter;
import ru.format.formatting.interfaces.ILexer;
import ru.format.formatting.interfaces.IToken;
import ru.format.io.interfaces.IWriter;

public class Formatter implements IFormatter {

    @Override
    public void format(final ILexer lexer, final IWriter writer)
            throws FormatterException, WriterException, ReaderException {
        Outputter outputter = new Outputter(writer);
        while (lexer.hasMoreTokens()) {
            IToken token = lexer.readToken();
            outputter.output(token);
        }
    }
}
