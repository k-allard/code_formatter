package ru.format;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

    public static List<Token> trimWhitespaces(List<Token> tokenList) {
        for (Token token : tokenList) {
            token.value = token.value.trim();
        }
        return tokenList;
    }

    public static void fixLevels(List<Token> tokenList) {
        int reduceFlag = 0;
        for (Token token : tokenList) {
            if (token.tokenType == TokenType.CLOSE) {
                reduceFlag++;
            }
            token.level -= reduceFlag;
            if (token.tokenType == TokenType.OPEN) {
                token.level = 0;
            }
        }
    }

    public static List<Token> deleteEmptyTokens(List<Token> tokenList) {
        List<Token> newTokenList = new ArrayList<>();
        for (Token token : tokenList) {
            if (token.tokenType == TokenType.OTHER && token.value.length() == 0) {
                continue;
            }
            newTokenList.add(token);
        }
        return newTokenList;
    }

    public static List<Token> splitByOpenBrackets(String fileString) {
        List<Token> tokenList = new ArrayList<>();
        String[] stringArray = fileString.split("[{]");
        if (stringArray.length == 1) {
            tokenList.add(new Token(stringArray[0], 0));
            return tokenList;
        }
        int level = 0;
        for (String str : stringArray) {
            tokenList.add(new Token(str, level));
            tokenList.add(new Token(TokenType.OPEN, level));
            level++;
        }
        tokenList.remove(tokenList.size() - 1);
        return tokenList;
    }

    public static List<Token> splitByNewLines(List<Token> tokenList) {
        List<Token> newTokenList = new ArrayList<>();
        for (Token token : tokenList) {
            if (token.tokenType == TokenType.OTHER) {
                String[] newValues = token.value.split("[\n]");
                if (newValues.length > 1) {
                    for (String newValue : newValues) {
                        newTokenList.add(new Token(newValue, token.level));
                    }
                } else {
                    newTokenList.add(token);
                }
            } else {
                newTokenList.add(token);
            }
        }
        return trimWhitespaces(newTokenList);
    }

    public static List<Token> splitByCloseBrackets(List<Token> tokenList) {
        List<Token> newTokenList = new ArrayList<>();
        for (Token token : tokenList) {
            if (token.tokenType != TokenType.OTHER) {
                newTokenList.add(token);
                continue;
            }
            String[] newValues = token.value.split("[}]");
            if (newValues.length > 1) {
                for (String newValue : newValues) {
                    newTokenList.add(new Token(newValue, token.level));
                    newTokenList.add(new Token(TokenType.CLOSE, token.level));
                }
                newTokenList.remove(newTokenList.size() - 1);
            } else {
                newTokenList.add(token);
            }
        }
        return trimWhitespaces(newTokenList);
    }

    public static List<Token> splitFileInTokens(String stringFile) {
        List<Token> tokens = splitByOpenBrackets(stringFile + " ");
        tokens = splitByCloseBrackets(tokens);
        tokens = splitByNewLines(tokens);
        tokens = deleteEmptyTokens(tokens);
        fixLevels(tokens);
        return tokens;
    }
}
