package ru.format.lexer;

import ru.format.exceptions.ReaderException;
import ru.format.io.IReader;

public class Lexer implements ILexer {
    private static final char   LEFT_CURLY_BRACKET = '{';
    private static final char   RIGHT_CURLY_BRACKET = '}';
    private static final char   SEMICOLON = ';';
    private static final String NEWLINE = "\n";
    private static final String MORE_THAN_ONE_SPACE = " +";
    private static final String SPACE = " ";
    private final IReader       reader;
    char                        charLastRead = '\0';
    boolean                     hasMoreTokens;

    public Lexer(IReader reader) throws ReaderException {
        this.reader = reader;
        hasMoreTokens = reader.hasChars();
    }

    String deleteExtraSpacesAndNewlines(String lexeme) {
        lexeme = lexeme.replaceAll(NEWLINE, "");
        lexeme = lexeme.trim();
        return lexeme.replaceAll(MORE_THAN_ONE_SPACE, SPACE);
    }

    @Override
    public IToken nextToken() throws ReaderException {
        if (charLastRead == LEFT_CURLY_BRACKET) {
            charLastRead = '\0';
            return new Token("OPEN", "{");
        } else if (charLastRead == RIGHT_CURLY_BRACKET) {
            charLastRead = '\0';
            return new Token("CLOSE", "}");
        } else if (charLastRead == SEMICOLON) {
            charLastRead = '\0';
            return new Token("SEMICOLON", ";");
        }
        StringBuilder textLexeme = new StringBuilder();
        while (reader.hasChars()) {
            char ch = reader.readChar();
            if (ch == LEFT_CURLY_BRACKET || ch == RIGHT_CURLY_BRACKET || ch == SEMICOLON) {
                if (textLexeme.length() != 0) {
                    charLastRead = ch;
                    hasMoreTokens = true;
                    return new Token("TEXT", deleteExtraSpacesAndNewlines(textLexeme.toString()));
                } else {
                    if (ch == LEFT_CURLY_BRACKET) {
                        return new Token("OPEN", "{");
                    } else if (ch == RIGHT_CURLY_BRACKET) {
                        return new Token("CLOSE", "}");
                    } else {
                        return new Token("SEMICOLON", ";");
                    }
                }
            } else {
                textLexeme.append(ch);
            }
        }
        hasMoreTokens = false;
        return new Token("TEXT", deleteExtraSpacesAndNewlines(textLexeme.toString()));
    }

    @Override
    public boolean hasMoreTokens() {
        return hasMoreTokens;
    }
}
