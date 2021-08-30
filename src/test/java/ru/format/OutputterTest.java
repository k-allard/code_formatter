package ru.format;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import ru.format.exceptions.WriterException;
//import ru.format.formatting.Lexeme;
import ru.format.formatting.Outputter;
//import ru.format.formatting.LexemeType;
import ru.format.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutputterTest {

    private Outputter outputter;
    private StringWriter out;

    @BeforeEach
    void setUp() {
        out = new StringWriter();
        outputter = new Outputter(out);
    }

    @Test
    void zeroLevelSpaces() {
        assertEquals("", outputter.getSpaces(0));
    }

    @Test
    void firstLevelSpaces() {
        assertEquals("    ", outputter.getSpaces(1));
    }

    @Test
    void fifthLevelSpaces() {
        assertEquals("                    ", outputter.getSpaces(5));
    }

//    @Test
//    void twoLevelsOutput() throws WriterException {
//
//        List<Lexeme> lexemeList = new ArrayList<>();
//        lexemeList.add(new Lexeme("public Response login(Request request) throws BadException", 0));
//        lexemeList.add(new Lexeme(LexemeType.OPEN, 0));
//        lexemeList.add(new Lexeme("User user = userService.get()", 1));
//        lexemeList.add(new Lexeme(LexemeType.SEMICOLON, 0));
//        lexemeList.add(new Lexeme("if (user == null)", 1));
//        lexemeList.add(new Lexeme(LexemeType.OPEN, 0));
//        lexemeList.add(new Lexeme("throw new BadRequestException(ErrorCode.INCORRECT_LOGIN, login)", 2));
//        lexemeList.add(new Lexeme(LexemeType.SEMICOLON, 0));
//        lexemeList.add(new Lexeme(LexemeType.CLOSE, 1));
//        lexemeList.add(new Lexeme("return loginDtoResponse", 1));
//        lexemeList.add(new Lexeme(LexemeType.SEMICOLON, 0));
//        lexemeList.add(new Lexeme(LexemeType.CLOSE, 0));
//        outputter.output(lexemeList);
//        assertEquals(
//            "public Response login(Request request) throws BadException {\n" +
//            "    User user = userService.get();\n" +
//            "    if (user == null) {\n" +
//            "        throw new BadRequestException(ErrorCode.INCORRECT_LOGIN, login);\n" +
//            "    }\n" +
//            "    return loginDtoResponse;\n" +
//            "}\n" ,
//
//            out.toString());
//    }
//
//    @Test
//    void ifClauseOutput() throws WriterException {
//
//        List<Lexeme> lexemeList = new ArrayList<>();
//        lexemeList.add(new Lexeme("if (start.minutes > stop.minutes)", 0));
//        lexemeList.add(new Lexeme(LexemeType.OPEN, 0));
//        lexemeList.add(new Lexeme("--stop.hours", 1));
//        lexemeList.add(new Lexeme(LexemeType.SEMICOLON, 0));
//        lexemeList.add(new Lexeme("stop.minutes += 60", 1));
//        lexemeList.add(new Lexeme(LexemeType.SEMICOLON, 0));
//        lexemeList.add(new Lexeme(LexemeType.CLOSE, 0));
//        outputter.output(lexemeList);
//        assertEquals(
//                "if (start.minutes > stop.minutes) {\n" +
//                        "    --stop.hours;\n" +
//                        "    stop.minutes += 60;\n" +
//                        "}\n" ,
//
//                out.toString());
//    }
}
