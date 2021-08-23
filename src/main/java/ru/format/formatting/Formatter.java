package ru.format.formatting;

import java.util.List;
import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.IWriter;

public class Formatter implements IFormatter {

    @Override
    public void format(ILexer lexer, IWriter writer) throws FormatterException, WriterException, ReaderException {
        List<Lexeme> lexemeList = lexer.getLexemes();
        Outputter outputter = new Outputter(writer);
        outputter.output(lexemeList);
    }
}
