package ru.format;

import lombok.extern.slf4j.Slf4j;
import ru.format.exceptions.CloseException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.formatting.FormatterStateMachine;
import ru.format.formatting.IFormatter;
import ru.format.io.FileReader;
import ru.format.io.FileWriter;
import ru.format.io.IReader;
import ru.format.io.IWriter;
import ru.format.lexer.ILexer;
import ru.format.lexer.LexerStateMachine;

@Slf4j
public class Main {

    private static final String outputFile = "code_output.txt";

    public static void main(String[] args)
            throws WriterException, ReaderException, CloseException, IllegalAccessException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Pass the filename as parameter!");
        }
        log.debug("Your input file is: " + args[0]);
        try (
                IReader reader = new FileReader(args[0]);
                IWriter writer = new FileWriter(outputFile)
        ) {
            IFormatter formatter = new FormatterStateMachine(writer);
            ILexer lexer = new LexerStateMachine(reader);
            formatter.format(lexer);
        }
    }
}
