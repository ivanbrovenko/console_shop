package com.epam.shop.service;

import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.BasketDAO;
import com.epam.shop.dao.OrderDAO;
import com.epam.shop.exception.IncorrectDateException;
import com.epam.shop.exception.NoClosestDateException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service to work with {@link OrderDAO}
 */
public interface OrderService {

    /**
     * Adds products to {@link OrderDAO}
     *
     * @param date date of making order
     * @param list list of orders
     */
    void add(LocalDateTime date, List<Gadget> list);

    /**
     * Returns {@link String} with list of order the most closest to specified date
     *
     * @param date user's input date
     * @return {@link String} with with list of order the most closest to specified date
     * @throws NoClosestDateException if order list is empty
     * @throws IncorrectDateException if date typed incorrectly
     */
    String showGadgetInClosestDate(String date) throws NoClosestDateException, IncorrectDateException;

    /**
     * Returns {@link String} with all orders
     *
     * @return {@link String} with all orders
     */
    String showAllOrders();

    /**
     * Returns all the orders in defined time
     *
     * @param first  min date
     * @param second max date
     * @return {@link List} with orders
     */
    List<Gadget> ordersInDefinedTime(String first, String second) throws IncorrectDateException;

    /**
     * Adds order to cache
     * Adds order to {@link OrderDAO}
     * Clears {@link BasketDAO}
     *
     * @param date date of the order
     * @return {@link String} price
     * @throws IncorrectDateException if date typed incorrectly
     */
    String makeOrder(String date) throws IncorrectDateException;
}
