package ru.format.formater;

import java.util.List;
import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.IReader;
import ru.format.io.IWriter;

public class Formatter implements IFormatter {

    @Override
    public void format(IReader reader, IWriter writer) throws FormatterException, WriterException, ReaderException {
        Lexer lexer = new Lexer(reader);
        List<Lexeme> lexemeList = lexer.getLexemes();
        Outputter outputter = new Outputter(writer);
        outputter.writeOutput(lexemeList);
    }
}
