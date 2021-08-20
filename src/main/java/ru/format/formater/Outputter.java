package ru.format.formater;

import java.util.List;
import ru.format.exceptions.WriterException;
import ru.format.parser.IWriter;

public class Outputter {

    private static final int SPACES_FOR_LEVEL = 4;
    private static final char SPACE = ' ';
    private static final char LEFT_CURLY_BRACKET = '{';
    private static final char RIGHT_CURLY_BRACKET = '}';
    private static final char SEMICOLON = ';';
    private static final char NEWLINE = '\n';

    private final IWriter writer;

    public void writeSpaces(int level) throws WriterException {
        for (int i = 0; i <  level * SPACES_FOR_LEVEL; i++) {
            writer.writeChar(SPACE);
        }
    }

    public void writeOutput(List<Token> tokenList) throws WriterException {
        for (Token token : tokenList) {
            writeSpaces(token.level);
            if (token.tokenType == TokenType.TEXT) {
                for (int i = 0; i < token.value.length(); i++) {
                    writer.writeChar(token.value.charAt(i));
                }
            } else if (token.tokenType == TokenType.OPEN) {
                writer.writeChar(SPACE);
                writer.writeChar(LEFT_CURLY_BRACKET);
                writer.writeChar(NEWLINE);
            } else if (token.tokenType == TokenType.CLOSE) {
                writer.writeChar(RIGHT_CURLY_BRACKET);
                writer.writeChar(NEWLINE);
            } else if (token.tokenType == TokenType.SEMICOLON) {
                writer.writeChar(SEMICOLON);
                writer.writeChar(NEWLINE);
            }
        }
    }

    public Outputter(IWriter writer) {
        this.writer = writer;
    }
}
