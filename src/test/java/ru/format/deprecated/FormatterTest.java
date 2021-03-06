//package ru.format.deprecated;
//
//import org.junit.jupiter.api.Test;
//import ru.format.exceptions.ReaderException;
//import ru.format.exceptions.WriterException;
//import ru.format.formatting.deprecated.Formatter;
//import ru.format.lexer.ILexer;
//import ru.format.lexer.deprecated.Lexer;
//import ru.format.io.StringReader;
//import ru.format.io.StringWriter;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class FormatterTest {
//
//    @Test
//    void formatTest() throws WriterException, ReaderException, IllegalAccessException {
//        var in = new StringReader("if (boo == null){ return 1;}\n" +
//                "else if (boo == 1)   {      return 2; }    \n" +
//                "   else \n" +
//                "{ if (boo > 10) \n" +
//                "   {\n" +
//                "boo -= 1;    \n" +
//                "return boo;\n" +
//                " }  \n" +
//                "      return 4;\n" +
//                "} ");
//        var out = new StringWriter();
//        Formatter formatter = new Formatter();
//        ILexer lexer = new Lexer(in);
//        formatter.format(lexer, out);
//
//        assertEquals("if (boo == null) {\n" +
//                "    return 1;\n" +
//                "}\n" +
//                "else if (boo == 1) {\n" +
//                "    return 2;\n" +
//                "}\n" +
//                "else {\n" +
//                "    if (boo > 10) {\n" +
//                "        boo -= 1;\n" +
//                "        return boo;\n" +
//                "    }\n" +
//                "    return 4;\n" +
//                "}\n"
//
//                , out.toString());
//    }
//
//    @Test
//    void formatTest2() throws WriterException, ReaderException, IllegalAccessException {
//        var in = new StringReader("    function { if (blabla == null) { return 1;\n" +
//                "    } if\n" +
//                " (...) { if (...) {\n" +
//                "    }\n" +
//                "        }\n" +
//                "    }");
//        var out = new StringWriter();
//        Formatter formatter = new Formatter();
//        ILexer lexer = new Lexer(in);
//        formatter.format(lexer, out);
//
//        assertEquals("function {\n" +
//                        "    if (blabla == null) {\n" +
//                        "        return 1;\n" +
//                        "    }\n" +
//                        "    if (...) {\n" +
//                        "        if (...) {\n" +
//                        "        }\n" +
//                        "    }\n" +
//                        "}\n"
//                , out.toString());
//    }
//
//    @Test
//    void formatTest3() throws WriterException, ReaderException, IllegalAccessException {
//        var in = new StringReader("public UserLoginResponse loginUser(UserLoginRequest request) throws BadRequestException\n" +
//                "{\n" +
//                "User user = userService.getUserByEmail(request.getEmail());\n" +
//                "if (user == null)\n" +
//                "{        throw new BadRequestException(ErrorCode.INCORRECT_LOGIN, \"login\"); }\n" +
//                "String token = tokenService.getToken(user);\n" +
//                "response.addHeader(SET_AUTH_HEADER_STRING, token);\n" +
//                "\n" +
//                "\n" +
//                "UserLoginResponse loginDtoResponse = new UserLoginResponse(token);\n" +
//                "return loginDtoResponse;      }");
//        var out = new StringWriter();
//        Formatter formatter = new Formatter();
//        ILexer lexer = new Lexer(in);
//        formatter.format(lexer, out);
//
//        assertEquals("public UserLoginResponse loginUser(UserLoginRequest request) throws BadRequestException {\n" +
//                        "    User user = userService.getUserByEmail(request.getEmail());\n" +
//                        "    if (user == null) {\n" +
//                        "        throw new BadRequestException(ErrorCode.INCORRECT_LOGIN, \"login\");\n" +
//                        "    }\n" +
//                        "    String token = tokenService.getToken(user);\n" +
//                        "    response.addHeader(SET_AUTH_HEADER_STRING, token);\n" +
//                        "    UserLoginResponse loginDtoResponse = new UserLoginResponse(token);\n" +
//                        "    return loginDtoResponse;\n" +
//                        "}\n"
//                , out.toString());
//    }
//
//    @Test
//    void formatTest4() throws WriterException, ReaderException, IllegalAccessException {
//        var in = new StringReader("if (boo == null){ return 1;}\n" +
//                "else if \n" +
//                " (boo == 1)   {      return 2; }    \n" +
//                "   else \n" +
//                "{ if (boo > 10) \n" +
//                "   {\n" +
//                "boo -= 1;    \n" +
//                "return boo;\n" +
//                " }  \n" +
//                "      return 4;\n" +
//                "} ");
//        var out = new StringWriter();
//        Formatter formatter = new Formatter();
//        ILexer lexer = new Lexer(in);
//        formatter.format(lexer, out);
//
//        assertEquals("if (boo == null) {\n" +
//                        "    return 1;\n" +
//                        "}\n" +
//                        "else if (boo == 1) {\n" +
//                        "    return 2;\n" +
//                        "}\n" +
//                        "else {\n" +
//                        "    if (boo > 10) {\n" +
//                        "        boo -= 1;\n" +
//                        "        return boo;\n" +
//                        "    }\n" +
//                        "    return 4;\n" +
//                        "}\n"
//                , out.toString());
//    }
//}
