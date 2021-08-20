package ru.format;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.format.exceptions.WriterException;
import ru.format.formater.Outputter;
import ru.format.formater.Token;
import ru.format.formater.TokenType;
import ru.format.parser.StringWriter;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OutputterTest {

    private static Outputter outputter;
    private static StringWriter out;

    @BeforeAll
    static void init() {
        out = new StringWriter();
        outputter = new Outputter(out);
    }

//    @Test
//    void addSpacesZeroLevel() {
//        assertEquals("", outputter.addSpaces(0));
//    }
//
//    @Test
//    void addSpacesFirstLevel() {
//        assertEquals("    ", outputter.addSpaces(1));
//    }
//
//    @Test
//    void addSpacesFifthLevel() {
//        assertEquals("                    ", outputter.addSpaces(5));
//    }

    @Test
    void getOutput() throws WriterException {

        List<Token> tokenList = new ArrayList<>();
        tokenList.add(new Token("public Response login(Request request) throws BadException", 0));
        tokenList.add(new Token(TokenType.OPEN, 0));
        tokenList.add(new Token("User user = userService.get()", 1));
        tokenList.add(new Token(TokenType.SEMICOLON, 0));
        tokenList.add(new Token("if (user == null)", 1));
        tokenList.add(new Token(TokenType.OPEN, 0));
        tokenList.add(new Token("throw new BadRequestException(ErrorCode.INCORRECT_LOGIN, login)", 2));
        tokenList.add(new Token(TokenType.SEMICOLON, 0));
        tokenList.add(new Token(TokenType.CLOSE, 1));
        tokenList.add(new Token("return loginDtoResponse", 1));
        tokenList.add(new Token(TokenType.SEMICOLON, 0));
        tokenList.add(new Token(TokenType.CLOSE, 0));
        outputter.writeOutput(tokenList);
        assertEquals(
            "public Response login(Request request) throws BadException {\n" +
            "    User user = userService.get();\n" +
            "    if (user == null) {\n" +
            "        throw new BadRequestException(ErrorCode.INCORRECT_LOGIN, login);\n" +
            "    }\n" +
            "    return loginDtoResponse;\n" +
            "}\n" ,

            out.toString());
    }
}
