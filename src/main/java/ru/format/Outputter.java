package ru.format;

import java.util.List;

public class Outputter {

    private static final int SPACES_FOR_LEVEL = 4;

    private static String getOutput(List<Token> tokenList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokenList.size(); i++) {
            sb.append(" ".repeat(Math.max(0, tokenList.get(i).level * SPACES_FOR_LEVEL)));
            if (tokenList.get(i).tokenType == TokenType.OTHER) {
                sb.append(tokenList.get(i).value);
                if (tokenList.get(i + 1).tokenType == TokenType.OTHER ||
                        tokenList.get(i + 1).tokenType == TokenType.CLOSE)
                    sb.append("\n");
            } else if (tokenList.get(i).tokenType == TokenType.OPEN) {
                sb.append(" {\n");
            } else if (tokenList.get(i).tokenType == TokenType.CLOSE) {
                sb.append("}\n");
            }
        }
        return sb.toString();
    }

    public static void printOutput(List<Token> tokenList) {
        System.out.print(getOutput(tokenList));
    }
}
