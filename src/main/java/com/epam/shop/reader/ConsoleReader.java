package com.epam.shop.reader;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Implementation of {@link Reader}
 */
public class ConsoleReader implements Reader {
    /**
     * Input stream variable
     */
    private InputStream input;
    /**
     * Scanner variable
     */
    private Scanner scanner;

    /**
     * Initial constructor
     *
     * @param input {@link InputStream}
     */
    public ConsoleReader(InputStream input) {
        this.input = input;
        this.scanner = new Scanner(input);
    }

    /**
     * Reads line
     *
     * @return {@link String}
     */
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Reads int type
     *
     * @return {@link Integer}
     */
    @Override
    public int readInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public BigDecimal readBigDecimal() {
        return new BigDecimal(Integer.parseInt(scanner.nextLine()));
    }
}
