package ru.format;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Pass the filename as parameter!");
        }
        System.err.println("Hi! This is code formatter");

        Parser parser = new Parser();
        Splitter splitter = new Splitter();
        Outputter outputter = new Outputter();
        System.err.println("R E S U L T :\n");
        System.out.print(outputter.getOutput(splitter.splitFileInTokens(parser.parseJavaFile(args[0]))));
    }
}
