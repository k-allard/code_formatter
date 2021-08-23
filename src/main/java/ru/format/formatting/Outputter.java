package ru.format.formatting;

import java.util.List;
import ru.format.exceptions.WriterException;
import ru.format.io.IWriter;

public class Outputter {

    private static final int    SPACES_FOR_LEVEL = 4;

    private final IWriter       writer;

    public Outputter(IWriter writer) {
        this.writer = writer;
    }

    public void writeString(String str) throws WriterException {
        for (int i = 0; i < str.length(); i++) {
            writer.writeChar(str.charAt(i));
        }
    }

    public String getSpaces(int level) {
        return (" ".repeat(Math.max(0, level * SPACES_FOR_LEVEL)));
    }

    public void output(List<Lexeme> lexemeList) throws WriterException {
        for (Lexeme lexeme : lexemeList) {
            writeString(getSpaces(lexeme.level));
            if (lexeme.lexemeType == LexemeType.TEXT) {
                writeString(lexeme.value);
            } else if (lexeme.lexemeType == LexemeType.OPEN) {
                writeString(" {\n");
            } else if (lexeme.lexemeType == LexemeType.CLOSE) {
                writeString("}\n");
            } else if (lexeme.lexemeType == LexemeType.SEMICOLON) {
                writeString(";\n");
            }
        }
    }
}
