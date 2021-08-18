package ru.format;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.format.formater.Splitter;
import ru.format.formater.Token;
import ru.format.formater.TokenType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitterTest {

    private static Splitter splitter;

    @BeforeAll
    static void init() {
        splitter = new Splitter();
    }

    @Test
    void splitShortFileInTokens() {
        List<Token> tokenListReceived = splitter.splitFileInTokens("if (blabla == null) \n" +
                "{\n" +
                "return 1;\n" +
                "  if (...) \n" +
                "  {\n" +
                "  if (...) { }\n" +
                "}} ");
        List<Token> tokenList = new ArrayList<>();
        tokenList.add(new Token("if (blabla == null)", 0));
        tokenList.add(new Token(TokenType.OPEN, 0));
        tokenList.add(new Token("return 1;", 1));
        tokenList.add(new Token("if (...)", 1));
        tokenList.add(new Token(TokenType.OPEN, 1));
        tokenList.add(new Token("if (...)", 2));
        tokenList.add(new Token(TokenType.OPEN, 2));
        tokenList.add(new Token(TokenType.CLOSE, 2));
        tokenList.add(new Token(TokenType.CLOSE, 1));
        tokenList.add(new Token(TokenType.CLOSE, 0));

        assertEquals(tokenList.toString(), tokenListReceived.toString());
    }

    @Test
    void splitLongFileInTokens() {
        List<Token> tokenListReceived = splitter.splitFileInTokens("if (boo == null){ return 1;}\n" +
                "else if (boo == 1)   {      return 2; }    \n" +
                "   else \n" +
                "{ if (boo > 10) \n" +
                "   {\n" +
                "boo -= 1;    \n" +
                "return boo;\n" +
                " }  \n" +
                "      return 4;\n" +
                "} ");
        List<Token> tokenList = new ArrayList<>();
        tokenList.add(new Token("if (boo == null)", 0));
        tokenList.add(new Token(TokenType.OPEN, 0));
        tokenList.add(new Token("return 1;", 1));
        tokenList.add(new Token(TokenType.CLOSE, 0));
        tokenList.add(new Token("else if (boo == 1)", 0));
        tokenList.add(new Token(TokenType.OPEN, 0));
        tokenList.add(new Token("return 2;", 1));
        tokenList.add(new Token(TokenType.CLOSE, 0));
        tokenList.add(new Token("else", 0));
        tokenList.add(new Token(TokenType.OPEN, 0));
        tokenList.add(new Token("if (boo > 10)", 1));
        tokenList.add(new Token(TokenType.OPEN, 1));
        tokenList.add(new Token("boo -= 1;", 2));
        tokenList.add(new Token("return boo;", 2));
        tokenList.add(new Token(TokenType.CLOSE, 1));
        tokenList.add(new Token("return 4;", 1));
        tokenList.add(new Token(TokenType.CLOSE, 0));

        assertEquals(tokenList.toString(), tokenListReceived.toString());
    }
}
