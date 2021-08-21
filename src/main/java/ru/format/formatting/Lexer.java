package ru.format.formatting;

import java.util.ArrayList;
import java.util.List;
import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;

public class Lexer {
    private static final char   LEFT_CURLY_BRACKET = '{';
    private static final char   RIGHT_CURLY_BRACKET = '}';
    private static final char   SEMICOLON = ';';
    private static final String NEWLINE = "\n";
    private static final String MORE_THAN_ONE_SPACE = " +";
    private static final String SPACE = " ";
    private final IReader       reader;

    void deleteExtraSpacesAndNewlines(List<Lexeme> lexemeList) {
        for (Lexeme lexeme : lexemeList) {
            lexeme.value = lexeme.value.replaceAll(NEWLINE, "");
            lexeme.value = lexeme.value.trim();
            lexeme.value = lexeme.value.replaceAll(MORE_THAN_ONE_SPACE, SPACE);
        }
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

    void deleteEmptyTokens(List<Lexeme> lexemeList) {
        for (int i = 0; i < lexemeList.size(); i++) {
            if (lexemeList.get(i).lexemeType == LexemeType.TEXT && lexemeList.get(i).value.length() == 0) {
                lexemeList.remove(i--);
            }
        }
    }

    void addTextLexeme(StringBuilder content, List<Lexeme> lexemeList, int levelCounter) {
        if (content.length() != 0) {
            lexemeList.add(new Lexeme(content.toString(), levelCounter));
            content.setLength(0);
        }
    }

    public List<Lexeme> getLexemes() throws ReaderException {
        StringBuilder content = new StringBuilder();
        List<Lexeme> lexemeList = new ArrayList<>();
        char ch;
        int levelCounter = 0;
        while (reader.hasChars()) {
            ch = reader.readChar();
            if (ch == LEFT_CURLY_BRACKET) {
                addTextLexeme(content, lexemeList, levelCounter);
                lexemeList.add(new Lexeme(LexemeType.OPEN, levelCounter++));
            } else if (ch == RIGHT_CURLY_BRACKET) {
                addTextLexeme(content, lexemeList, levelCounter);
                lexemeList.add(new Lexeme(LexemeType.CLOSE, levelCounter));
            } else if (ch == SEMICOLON) {
                addTextLexeme(content, lexemeList, levelCounter);
                lexemeList.add(new Lexeme(LexemeType.SEMICOLON, levelCounter));
            } else {
                content.append(ch);
            }
        }
        deleteExtraSpacesAndNewlines(lexemeList);
        deleteEmptyTokens(lexemeList);
        fixLevels(lexemeList);
        return lexemeList;
    }

    public Lexer(IReader reader) {
        this.reader = reader;
    }
}
