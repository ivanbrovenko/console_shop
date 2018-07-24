package com.epam.shop.reader;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Reader interface
 */
public interface Reader {
    /**
     * Reads {@link String} from some source
     *
     * @return {@link String}
     */
    String readLine();

    /**
     * Check if there's a next line
     *
     * @return
     */
    boolean hasNextLine();

    /**
     * Reads {@link Integer} type
     *
     * @return {@link Integer}
     */
    int readInt();

    BigDecimal readBigDecimal();

    default Map<Class<?>,String> getMethods(){
        Map<Class<?>,String > methods = new HashMap<>();
        methods.put(String.class,"readLine");
        methods.put(int.class,"readInt");
        methods.put(BigDecimal.class,"readBigDecimal");
        return methods;
    }
}
