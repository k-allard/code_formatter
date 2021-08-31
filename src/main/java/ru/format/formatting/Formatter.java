package ru.format.formatting;

import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.FormatterException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.io.IWriter;
import ru.format.lexer.ILexer;
import ru.format.lexer.IToken;

@Slf4j
public class Formatter implements IFormatter {

    @Override
    public void format(final ILexer lexer, final IWriter writer)
            throws FormatterException, WriterException, ReaderException {
        Outputter outputter = new Outputter(writer);
        while (lexer.hasMoreTokens()) {
            IToken token = lexer.nextToken();       // Each readToken() call starts the Lexer State Machine
            log.debug("Token: name [{}], lexeme [{}]", token.getName(), token.getLexeme());
            //            outputter.output(token);
        }
    }
}
