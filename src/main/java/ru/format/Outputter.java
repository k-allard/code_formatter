package ru.format;

import java.util.List;

public class Outputter {

    private static final int SPACES_FOR_LEVEL = 4;


    public static String addSpaces(int level) {
        return(" ".repeat(Math.max(0, level * SPACES_FOR_LEVEL)));
    }

    public static String getOutput(List<Token> tokenList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokenList.size(); i++) {
            sb.append(addSpaces(tokenList.get(i).level));
            if (tokenList.get(i).tokenType == TokenType.OTHER) {
                sb.append(tokenList.get(i).value);
                if (i + 1 < tokenList.size() && (tokenList.get(i + 1).tokenType == TokenType.OTHER
                        || tokenList.get(i + 1).tokenType == TokenType.CLOSE)) {
                    sb.append("\n");
                }
            } else if (tokenList.get(i).tokenType == TokenType.OPEN) {
                sb.append(" {\n");
            } else if (tokenList.get(i).tokenType == TokenType.CLOSE) {
                sb.append("}\n");
            }
        }
        return sb.toString();
    }
}
