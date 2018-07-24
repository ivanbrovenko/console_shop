package com.epam.shop.dao;

import com.epam.shop.entity.Gadget;

import java.util.Map;

/**
 * BasketDAO
 */
public interface BasketDAO {

    /**
     * Method for adding a new gadget to the basket
     *
     * @param gadget object of a Gadget class
     * @throws NullPointerException in gadget value is null
     */
    void add(Gadget gadget);

    /**
     * Method that gets quantity of a gadget in a basket
     *
     * @param serial serial number of a gadget
     * @return quantity of a certain gadget unit
     */
    int getQuantityBySerialNumber(String serial);

    /**
     * Method for checking whether there's a gadget
     * with a certain serial in a basket.
     *
     * @param serial serial number of a certain gadget
     * @return <tt>true</tt> if  {@link BasketDAO} contains product with defined serial
     */
    boolean containsSerial(String serial);

    /**
     * Clears the basket
     */
    void clear();

    /**
     * Returns unmodifiable basket
     *
     * @return unmodifiable {@link Map}
     */
    Map<String, Integer> getGadgetListFromBasketWithKeys();
}
