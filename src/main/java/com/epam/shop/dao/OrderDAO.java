package com.epam.shop.dao;

import com.epam.shop.entity.Gadget;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NavigableMap;

/**
 * OrderDAO
 */
public interface OrderDAO {

    /**
     * Adds new object to OrderDAO
     * @param date date of adding a new gadget
     * @param list list of orders
     */
    void add(LocalDateTime date, List<Gadget> list);

    /**
     * Returns list of gadgets with dates
     * @return list of gadgets
     */
    NavigableMap<LocalDateTime, List<Gadget>> getGadgetListWithKeys();
}
