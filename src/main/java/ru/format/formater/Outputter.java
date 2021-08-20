package ru.format.formater;

import java.util.List;

public class Outputter {

    private static final int SPACES_FOR_LEVEL = 4;

    public String addSpaces(int level) {
        return (" ".repeat(Math.max(0, level * SPACES_FOR_LEVEL)));
    }

    public String getOutput(List<Token> tokenList) {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokenList) {
            sb.append(addSpaces(token.level));
            if (token.tokenType == TokenType.TEXT) {
                sb.append(token.value);
            } else if (token.tokenType == TokenType.OPEN) {
                sb.append(" {\n");
            } else if (token.tokenType == TokenType.CLOSE) {
                sb.append("}\n");
            } else if (token.tokenType == TokenType.SEMICOLON) {
                sb.append(";\n");
            }
        }
        return sb.toString();
    }
}
