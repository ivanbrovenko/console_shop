package com.epam.shop.reader;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Implementation of {@link Reader} that generates random value of different types
 */
public class RandomGeneratorReader implements Reader {

    /**
     * Generates random string
     *
     * @return {@link String}
     */
    @Override
    public String readLine() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < 5) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();
    }

    /**
     * Unsupported operation
     */
    @Override
    public boolean hasNextLine() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns random int value
     *
     * @return {@link Integer}
     */
    @Override
    public int readInt() {
        Random rnd = new Random();
        return rnd.nextInt(1000);
    }

    @Override
    public BigDecimal readBigDecimal() {
        Random rnd = new Random();
        return new BigDecimal(rnd.nextInt(1000));
    }

}
