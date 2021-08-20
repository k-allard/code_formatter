package ru.format.formater;

import java.util.ArrayList;
import java.util.List;
import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;

public class Lexer {
    private static final char   LEFT_CURLY_BRACKET = '{';
    private static final char   RIGHT_CURLY_BRACKET = '}';
    private static final char   SEMICOLON = ';';
    private static final String NEWLINE = "\n";
    private final IReader       reader;

    List<Lexeme> trimWhitespaces(List<Lexeme> lexemeList) {
        for (Lexeme lexeme : lexemeList) {
            if (lexeme.value.contains(NEWLINE)) {
                lexeme.value = lexeme.value.replace(NEWLINE, "");
            }
            lexeme.value = lexeme.value.trim();
            if (lexeme.value.contains("  ")) {
                lexeme.value = lexeme.value.replaceAll(" +", " ");
            }
        }
        return lexemeList;
    }

    void fixLevels(List<Lexeme> lexemeList) {
        int reduceFlag = 0;
        for (Lexeme lexeme : lexemeList) {
            if (lexeme.lexemeType == LexemeType.CLOSE) {
                reduceFlag++;
            }
            lexeme.level -= reduceFlag;
            if (lexeme.lexemeType == LexemeType.OPEN || lexeme.lexemeType == LexemeType.SEMICOLON) {
                lexeme.level = 0;
            }
        }
    }

    List<Lexeme> deleteEmptyTokens(List<Lexeme> lexemeList) {
        List<Lexeme> newLexemeList = new ArrayList<>();
        for (Lexeme lexeme : lexemeList) {
            if (lexeme.lexemeType == LexemeType.TEXT && lexeme.value.length() == 0) {
                continue;
            }
            newLexemeList.add(lexeme);
        }
        return newLexemeList;
    }

    public List<Lexeme> getLexemes() throws ReaderException {
        StringBuilder content = new StringBuilder();
        char ch;
        List<Lexeme> lexemeList = new ArrayList<>();
        int levelCounter = 0;
        while (reader.hasChars()) {
            ch = reader.readChar();
            if (ch == LEFT_CURLY_BRACKET) {
                if (content.length() != 0) {
                    lexemeList.add(new Lexeme(content.toString(), levelCounter));
                    content.setLength(0);
                }
                lexemeList.add(new Lexeme(LexemeType.OPEN, levelCounter++));
            } else if (ch == RIGHT_CURLY_BRACKET) {
                if (content.length() != 0) {
                    lexemeList.add(new Lexeme(content.toString(), levelCounter));
                    content.setLength(0);
                }
                lexemeList.add(new Lexeme(LexemeType.CLOSE, levelCounter));
            } else if (ch == SEMICOLON) {
                if (content.length() != 0) {
                    lexemeList.add(new Lexeme(content.toString(), levelCounter));
                    content.setLength(0);
                }
                lexemeList.add(new Lexeme(LexemeType.SEMICOLON, levelCounter));
            } else {
                content.append(ch);
            }
        }
        lexemeList = deleteEmptyTokens(trimWhitespaces(lexemeList));
        fixLevels(lexemeList);
        return lexemeList;
    }

    public Lexer(IReader reader) {
        this.reader = reader;
    }
}
