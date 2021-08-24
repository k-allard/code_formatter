package ru.format.formatting;

import ru.format.exceptions.WriterException;
import ru.format.formatting.interfaces.IToken;
import ru.format.io.interfaces.IWriter;

public class Outputter {

    private static final int SPACES_FOR_LEVEL = 4;
    int levelCounter = 0;

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

    public void output(IToken token) throws WriterException {
        if (token.getName().equals("TEXT") && token.getLexeme().length() != 0) {
            writeString(getSpaces(levelCounter));
            writeString(token.getLexeme());
        } else if (token.getName().equals("OPEN")) {
            levelCounter++;
            writeString(" {\n");
        } else if (token.getName().equals("CLOSE")) {
            levelCounter--;
            writeString(getSpaces(levelCounter));
            writeString("}\n");
        } else if (token.getName().equals("SEMI")) {
            writeString(";\n");
        }
    }
}
