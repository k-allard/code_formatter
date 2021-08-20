package ru.format;

import ru.format.exceptions.CloseException;
import ru.format.exceptions.ReaderException;
import ru.format.exceptions.WriterException;
import ru.format.formater.Formatter;
import ru.format.parser.FileReaderMy;
import ru.format.parser.FileWriterMy;

public class Main {
    private static final String outputFile = "code_output.txt";

    public static void main(String[] args) {

        if (args.length != 1) {
            throw new IllegalArgumentException("Pass the filename as parameter!");
        }
        System.out.println("Hi! This is code formatter");
        System.out.println("Your input file is: " + args[0]);

        Formatter formatter = new Formatter();

        try (
                var in = new FileReaderMy(args[0]);
                var out = new FileWriterMy(outputFile)
        ) {
            formatter.format(in, out);
        } catch (WriterException | ReaderException | CloseException e) {
            e.printStackTrace();
        }
        System.out.println("Success.");
        System.out.println("Result can be found in " + outputFile);
    }
}
