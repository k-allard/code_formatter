package ru.format;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Pass the filename as parameter!");
        }
        System.out.println("Hi! This is code formatter");
        System.out.println("R E S U L T :\n");
        Outputter.printOutput(Splitter.splitFileInTokens(Parser.parseJavaFile(args[0])));
    }
}
