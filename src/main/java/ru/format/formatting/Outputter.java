package ru.format.formatting;

import java.util.List;
import ru.format.exceptions.WriterException;
import ru.format.io.IWriter;

public class Outputter {

    private static final int    SPACES_FOR_LEVEL = 4;
    private static final char   SPACE = ' ';
    private static final char   LEFT_CURLY_BRACKET = '{';
    private static final char   RIGHT_CURLY_BRACKET = '}';
    private static final char   SEMICOLON = ';';
    private static final char   NEWLINE = '\n';

    private final IWriter       writer;

    public void writeSpaces(int level) throws WriterException {
        for (int i = 0; i <  level * SPACES_FOR_LEVEL; i++) {
            writer.writeChar(SPACE);
        }
    }

    public void output(List<Lexeme> lexemeList) throws WriterException {
        for (Lexeme lexeme : lexemeList) {
            writeSpaces(lexeme.level);
            if (lexeme.lexemeType == LexemeType.TEXT) {
                for (int i = 0; i < lexeme.value.length(); i++) {
                    writer.writeChar(lexeme.value.charAt(i));
                }
            } else if (lexeme.lexemeType == LexemeType.OPEN) {
                writer.writeChar(SPACE);
                writer.writeChar(LEFT_CURLY_BRACKET);
                writer.writeChar(NEWLINE);
            } else if (lexeme.lexemeType == LexemeType.CLOSE) {
                writer.writeChar(RIGHT_CURLY_BRACKET);
                writer.writeChar(NEWLINE);
            } else if (lexeme.lexemeType == LexemeType.SEMICOLON) {
                writer.writeChar(SEMICOLON);
                writer.writeChar(NEWLINE);
            }
        }
    }

    public Outputter(IWriter writer) {
        this.writer = writer;
    }
}
