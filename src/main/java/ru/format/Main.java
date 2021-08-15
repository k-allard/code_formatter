package ru.format;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Pass the filename as parameter!");
        }
        System.err.println("Hi! This is code formatter");
        System.err.println("R E S U L T :\n");
        Outputter.printOutput(Splitter.splitFileInTokens(Parser.parseJavaFile(args[0])));
    }
}
