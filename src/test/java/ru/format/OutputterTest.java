package ru.format;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.format.exceptions.WriterException;
import ru.format.formatting.Lexeme;
import ru.format.formatting.Outputter;
import ru.format.formatting.LexemeType;
import ru.format.io.StringWriter;

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

        List<Lexeme> lexemeList = new ArrayList<>();
        lexemeList.add(new Lexeme("public Response login(Request request) throws BadException", 0));
        lexemeList.add(new Lexeme(LexemeType.OPEN, 0));
        lexemeList.add(new Lexeme("User user = userService.get()", 1));
        lexemeList.add(new Lexeme(LexemeType.SEMICOLON, 0));
        lexemeList.add(new Lexeme("if (user == null)", 1));
        lexemeList.add(new Lexeme(LexemeType.OPEN, 0));
        lexemeList.add(new Lexeme("throw new BadRequestException(ErrorCode.INCORRECT_LOGIN, login)", 2));
        lexemeList.add(new Lexeme(LexemeType.SEMICOLON, 0));
        lexemeList.add(new Lexeme(LexemeType.CLOSE, 1));
        lexemeList.add(new Lexeme("return loginDtoResponse", 1));
        lexemeList.add(new Lexeme(LexemeType.SEMICOLON, 0));
        lexemeList.add(new Lexeme(LexemeType.CLOSE, 0));
        outputter.writeOutput(lexemeList);
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
