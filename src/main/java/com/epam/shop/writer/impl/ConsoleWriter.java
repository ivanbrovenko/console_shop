package com.epam.shop.writer.impl;

import com.epam.shop.writer.Writer;

import java.io.PrintStream;

/**
 * Writer implementation
 */
public class ConsoleWriter implements Writer {
    /**
     * PrintStream that should be initialized
     */
    private PrintStream printStream;

    /**
     * Initial constructor
     * @param printStream initialization for PrintStream variable
     */
    public ConsoleWriter(PrintStream printStream) {
        this.printStream = printStream;
    }

    /**
     * Writer to some source
     * @param line {@link String} argument
     */
    @Override
    public void writeLine(String line) {
        printStream.println(line);
    }
}
