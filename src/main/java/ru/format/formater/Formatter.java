package ru.format.formater;

import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.parser.IReader;
import ru.format.parser.IWriter;

import java.util.ArrayList;
import java.util.List;

public class Formatter implements IFormatter {

    @Override
    public void format(IReader reader, IWriter writer) throws FormatterException, WriterException, ReaderException {
        StringBuilder content = new StringBuilder();
        char ch;
        List<Token> tokenList = new ArrayList<>();
        int levelCounter = 0;
        while (reader.hasChars()) {
            ch = reader.readChar();
            if (ch == '{') {
                if (content.length() != 0) {
                    tokenList.add(new Token(content.toString(), levelCounter));
                    content.setLength(0);
                }
                tokenList.add(new Token(TokenType.OPEN, levelCounter++));
            } else if (ch == '}') {
                tokenList.add(new Token(TokenType.CLOSE, levelCounter--));
            } else if (ch == '\n') {
                tokenList.add(new Token(TokenType.TEXT, levelCounter));
            }
            content.append(ch);
        }

        Splitter splitter = new Splitter();
//        List<Token> tokenList = splitter.splitFileInTokens(content.toString());

        Outputter outputter = new Outputter();
        String result = outputter.getOutput(tokenList);
        for (int i = 0; i < result.length(); i++) {
            writer.writeChar(result.charAt(i));
        }
    }
}
