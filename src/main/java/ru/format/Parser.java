package ru.format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Parser {

    public static String readAllLinesWithStream(BufferedReader reader) {
        return reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }

    static String parseJavaFile(String fileName) throws IOException {
        String allFileInOneString;
        try (
                FileReader fileReader = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fileReader)
        ) {
            allFileInOneString = readAllLinesWithStream(br);
            return allFileInOneString;
        }

    }
}
