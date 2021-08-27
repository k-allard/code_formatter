package ru.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.format.exceptions.CloseException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.formatting.Formatter;
import ru.format.lexer.Lexer;
import ru.format.formatting.IFormatter;
import ru.format.lexer.ILexer;
import ru.format.io.FileReader;
import ru.format.io.FileWriter;
import ru.format.io.IReader;
import ru.format.io.IWriter;
import ru.format.lexer.LexerStateMachine;

public class Main {

    private static final String outputFile = "code_output.txt";
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws WriterException, ReaderException, CloseException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Pass the filename as parameter!");
        }
        logger.info("Your input file is: " + args[0]);
        IFormatter formatter = new Formatter();
        try (
                IReader reader = new FileReader(args[0]);
                IWriter writer = new FileWriter(outputFile)
        ) {
            ILexer lexer = new LexerStateMachine(reader);
            formatter.format(lexer, writer);        // Each format() call starts the Formatter State Machine
        }
        logger.info("File formatted.");
        logger.info("Result can be found in " + outputFile);
    }
}
