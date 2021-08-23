package ru.format;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.format.exceptions.CloseException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.formatting.Formatter;
import ru.format.formatting.ILexer;
import ru.format.formatting.Lexer;
import ru.format.io.FileReader;
import ru.format.io.FileWriter;
import ru.format.io.IReader;
import ru.format.io.IWriter;

public class Main {

    private static final String outputFile = "code_output.txt";
    private static final Log logger = LogFactory.getLog(Main.class);        //TODO поменять на SLF4J

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Pass the filename as parameter!");
        }
        System.out.println("Hi! This is code formatter");
        System.out.println("Your input file is: " + args[0]);
        Formatter formatter = new Formatter();
        try (
                IReader reader = new FileReader(args[0]);
                IWriter writer = new FileWriter(outputFile)
        ) {
            ILexer lexer = new Lexer(reader);
            formatter.format(lexer, writer);
        } catch (WriterException | ReaderException | CloseException e) {
            logger.error("Exception catched in Main", e);
            return;
        }
        System.out.println("Success.");
        System.out.println("Result can be found in " + outputFile);
    }
}
