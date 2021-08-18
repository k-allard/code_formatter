package ru.format;

import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.parser.IReader;
import ru.format.parser.IWriter;

public class Formatter implements IFormatter {

    @Override
    public void format(IReader reader, IWriter writer) throws FormatterException, WriterException, ReaderException {
        StringBuilder content = new StringBuilder();

        while (reader.hasChars()) {
            content.append(reader.readChar());
        }

        Splitter splitter = new Splitter();
        Outputter outputter = new Outputter();
        String result = outputter.getOutput(splitter.splitFileInTokens(content.toString()));
        for (int i = 0; i < result.length(); i++) {
            writer.writeChar(result.charAt(i));
        }
    }
}
