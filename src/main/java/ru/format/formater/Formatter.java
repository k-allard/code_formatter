package ru.format.formater;

import java.util.ArrayList;
import java.util.List;
import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.parser.IReader;
import ru.format.parser.IWriter;

public class Formatter implements IFormatter {

    List<Token> trimWhitespaces(List<Token> tokenList) {
        for (Token token : tokenList) {
            if (token.value.contains("\n")) {
                token.value = token.value.replace("\n", "");
            }
            token.value = token.value.trim();
            if (token.value.contains("  ")) {
                token.value = token.value.replaceAll(" +", " ");
            }
        }
        return tokenList;
    }

    void fixLevels(List<Token> tokenList) {
        int reduceFlag = 0;
        for (Token token : tokenList) {
            if (token.tokenType == TokenType.CLOSE) {
                reduceFlag++;
            }
            token.level -= reduceFlag;
            if (token.tokenType == TokenType.OPEN || token.tokenType == TokenType.SEMICOLON) {
                token.level = 0;
            }
        }
    }

    List<Token> deleteEmptyTokens(List<Token> tokenList) {
        List<Token> newTokenList = new ArrayList<>();
        for (Token token : tokenList) {
            if (token.tokenType == TokenType.TEXT && token.value.length() == 0) {
                continue;
            }
            newTokenList.add(token);
        }
        return newTokenList;
    }

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
                if (content.length() != 0) {
                    tokenList.add(new Token(content.toString(), levelCounter));
                    content.setLength(0);
                }
                tokenList.add(new Token(TokenType.CLOSE, levelCounter));
            } else if (ch == ';') {
                if (content.length() != 0) {
                    tokenList.add(new Token(content.toString(), levelCounter));
                    content.setLength(0);
                }
                tokenList.add(new Token(TokenType.SEMICOLON, levelCounter));
            } else {
                content.append(ch);
            }
        }

        tokenList = deleteEmptyTokens(trimWhitespaces(tokenList));
        fixLevels(tokenList);

        // Splitter splitter = new Splitter();
        // List<Token> tokenList = splitter.splitFileInTokens(content.toString());

        Outputter outputter = new Outputter();
        String result = outputter.getOutput(tokenList);
        for (int i = 0; i < result.length(); i++) {
            writer.writeChar(result.charAt(i));
        }
    }
}
