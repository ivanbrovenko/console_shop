package com.epam.shop.dao.impl;

import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.OrderDAO;

import java.time.LocalDateTime;
import java.util.*;

/**
 * DAO implementation that contains lit with orders
 */
public class OrderDAOImpl implements OrderDAO {
    /**
     * Container that contains date and list of gadgets
     */
    private NavigableMap<LocalDateTime, List<Gadget>> orders;

    /**
     * Initial constructor
     */
    public OrderDAOImpl() {
        orders = new TreeMap<>();
    }

    /**
     * Method for adding date and lit of gadgets to inner container
     *
     * @param date date of adding
     * @param list list of gadgets
     */
    @Override
    public void add(LocalDateTime date, List<Gadget> list) {
        orders.put(date, list);
    }

    /**
     * Method that returning list of gadgets
     *
     * @return list of gadgets
     */
    public List<Gadget> getGadgetList() {
        List<Gadget> gadgets = new ArrayList<>();
        orders.entrySet().stream().forEach((s) -> s.getValue().forEach((s1) -> gadgets.add(s1)));
        return gadgets;
    }

    /**
     * Method that returns inner container
     *
     * @return inner container
     */
    @Override
    public NavigableMap<LocalDateTime, List<Gadget>> getGadgetListWithKeys() {
        return Collections.unmodifiableNavigableMap(orders);
    }
}
