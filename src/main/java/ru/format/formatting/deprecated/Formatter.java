//package ru.format.formatting.deprecated;
//
//import lombok.extern.slf4j.Slf4j;
//import ru.format.exceptions.FormatterException;
//import ru.format.exceptions.ReaderException;
//import ru.format.exceptions.WriterException;
//import ru.format.formatting.FormatterState;
//import ru.format.formatting.IFormatter;
//import ru.format.io.IWriter;
//import ru.format.lexer.ILexer;
//import ru.format.lexer.IToken;
//
//@Slf4j
//public class Formatter implements IFormatter {
//
//    @Override
//    public void format(final ILexer lexer)
//            throws FormatterException, WriterException, ReaderException, IllegalAccessException {
//        while (lexer.hasMoreTokens()) {
//            IToken token = lexer.nextToken();
//            log.debug("NEW TOKEN [{}] - [{}]", token.getName(), token.getLexeme());
//        }
//    }
//}
