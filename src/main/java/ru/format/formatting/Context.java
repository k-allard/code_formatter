package ru.format.formatting;

import ru.format.exceptions.WriterException;
import ru.format.io.IWriter;
import ru.format.lexer.IToken;

public class Context implements IContext {

    private static final int SPACES_FOR_LEVEL = 4;
    private int indentLevel;
    private final IWriter writer;

    public Context(IWriter writer) {
        indentLevel = 0;
        this.writer = writer;
    }

    private void writeString(String str) throws WriterException {
        for (int i = 0; i < str.length(); i++) {
            writer.writeChar(str.charAt(i));
        }
    }

    @Override
    public void writeLexeme(IToken token) throws WriterException {
        writeString(token.getLexeme());
    }

    @Override
    public void writeNewLine() throws WriterException {
        writer.writeChar('\n');
    }

    @Override
    public void writeIndent() throws WriterException {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < indentLevel * SPACES_FOR_LEVEL; i++) {
            indent.append(' ');
        }
        writeString(indent.toString());
    }

    @Override
    public void incrementIndent() {
        indentLevel++;
    }

    @Override
    public void decrementIndent() {
        indentLevel--;
    }

    @Override
    public void writeSpace() throws WriterException {
        writer.writeChar(' ');
    }
}
