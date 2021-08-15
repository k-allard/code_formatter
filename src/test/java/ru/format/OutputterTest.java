package ru.format;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OutputterTest {

    @Test
    void addSpacesZeroLevel() {
        assertEquals("", Outputter.addSpaces(0));
    }

    @Test
    void addSpacesFirstLevel() {
        assertEquals("    ", Outputter.addSpaces(1));
    }

    @Test
    void addSpacesFifthLevel() {
        assertEquals("                    ", Outputter.addSpaces(5));
    }

    @Test
    void getOutput() {

        List<Token> tokenList = new ArrayList<>();
        tokenList.add(new Token("public Response login(Request request) throws BadException", 0));
        tokenList.add(new Token(TokenType.OPEN, 0));
        tokenList.add(new Token("User user = userService.get();", 1));
        tokenList.add(new Token("if (user == null)", 1));
        tokenList.add(new Token(TokenType.OPEN, 0));
        tokenList.add(new Token("throw new BadRequestException(ErrorCode.INCORRECT_LOGIN, login);", 2));
        tokenList.add(new Token(TokenType.CLOSE, 1));
        tokenList.add(new Token("return loginDtoResponse;", 1));
        tokenList.add(new Token(TokenType.CLOSE, 0));

        assertEquals(
                "public Response login(Request request) throws BadException {\n" +
                "    User user = userService.get();\n" +
                "    if (user == null) {\n" +
                "        throw new BadRequestException(ErrorCode.INCORRECT_LOGIN, login);\n" +
                "    }\n" +
                "    return loginDtoResponse;\n" +
                "}\n" ,

                Outputter.getOutput(tokenList));
    }
}
