package com.epam.shop.dao.impl;

import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.BasketDAO;

import java.util.*;

/**
 * Container for gadgets
 */
public class BasketDAOImpl implements BasketDAO {
    /**
     * Map that contains serials and quantity of gadgets
     */
    private Map<String, Integer> basket;

    /**
     * Constructor for initializing basket object
     */
    public BasketDAOImpl() {
        basket = new LinkedHashMap<>();
    }

    /**
     * Method for adding a new gadget to the basket
     *
     * @param gadget object of a Gadget class
     * @throws NullPointerException in gadget value is null
     */
    @Override
    public void add(Gadget gadget) {
        Objects.requireNonNull(gadget);
        if (basket.containsKey(gadget.getSerialNumber())) {
            int quantity = basket.get(gadget.getSerialNumber());
            basket.put(gadget.getSerialNumber(), quantity + 1);
        } else {
            basket.put(gadget.getSerialNumber(), 1);
        }
    }

    /**
     * Method that gets quantity of a gadget in a basket
     *
     * @param serial serial number of a gadget
     * @return quantity of a certain gadget unit
     */
    @Override
    public int getQuantityBySerialNumber(String serial) {
        return basket.get(serial);
    }

    /**
     * Method for checking whether there's a gadget
     * with a certain serial in a basket.
     *
     * @param serial serial number of a certain gadget
     * @return <tt>true</tt> if  {@link BasketDAO} contains product with defined serial
     */
    @Override
    public boolean containsSerial(String serial) {
        return basket.containsKey(serial);
    }

    /**
     * Method for clearing the basket
     */
    @Override
    public void clear() {
        basket.clear();
    }

    /**
     * Returns unmodifiable basket
     *
     * @return unmodifiable {@link Map}
     */
    @Override
    public Map<String, Integer> getGadgetListFromBasketWithKeys() {
        return Collections.unmodifiableMap(basket);
    }
}
