package com.epam.shop.service.impl;

import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.BasketDAO;
import com.epam.shop.dao.OrderDAO;
import com.epam.shop.dao.ProductDAO;
import com.epam.shop.exception.IncorrectDateException;
import com.epam.shop.exception.NoClosestDateException;
import com.epam.shop.cache.LRUCache;
import com.epam.shop.messages.Messages;
import com.epam.shop.service.OrderService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Implementation of {@link OrderService}
 */
public class OrderServiceImpl implements OrderService {
    /**
     * DAO to work with
     */
    private OrderDAO orderDAO;
    /**
     * DAO to work with
     */
    private ProductDAO productDAO;
    /**
     * DAO to word with
     */
    private BasketDAO basketDAO;
    /**
     * Cache to add products from list
     */
    private LRUCache cache;

    /**
     * Initial constructor
     *
     * @param orderDAO   {@link OrderDAO} argument to initialize
     * @param productDAO {@link ProductDAO} argument to initialize
     * @param basketDAO  {@link BasketDAO} argument to initialize
     * @param cache      {@link LRUCache} argument to initialize
     */
    public OrderServiceImpl(OrderDAO orderDAO, ProductDAO productDAO, BasketDAO basketDAO, LRUCache cache) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.basketDAO = basketDAO;
        this.cache = cache;
    }

    /**
     * Adds products to {@link OrderDAO}
     *
     * @param date date of making order
     * @param list list of orders
     */
    @Override
    public void add(LocalDateTime date, List<Gadget> list) {
        orderDAO.add(date, list);
    }

    /**
     * Returns {@link String} with list of order the most closest to specified date
     *
     * @param date user's date date
     * @return {@link String} with with list of order the most closest to specified date
     * @throws NoClosestDateException if order list is empty
     * @throws IncorrectDateException if date typed incorrectly
     */
    @Override
    public String showGadgetInClosestDate(String date) throws NoClosestDateException, IncorrectDateException {
        List<Gadget> list = new ArrayList<>();
        StringJoiner stringJoiner = new StringJoiner("\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        LocalDateTime ldt;
        try {
            LocalDateTime inputDate = LocalDateTime.parse(date, formatter);
            Map.Entry<LocalDateTime, List<Gadget>> floor = orderDAO.getGadgetListWithKeys().floorEntry(inputDate);
            Map.Entry<LocalDateTime, List<Gadget>> higher = orderDAO.getGadgetListWithKeys().higherEntry(inputDate);

            if (floor != null && higher != null) {
                if (Duration.between(floor.getKey(), inputDate).compareTo(Duration.between(higher.getKey(), inputDate)) > 0) {
                    list = floor.getValue();
                    ldt = floor.getKey();
                } else {
                    list = higher.getValue();
                    ldt = higher.getKey();
                }
            }
            if (floor != null) {
                list = floor.getValue();
                ldt = floor.getKey();
            } else if (higher != null) {
                list = higher.getValue();
                ldt = higher.getKey();
            } else {
                throw new NoClosestDateException("There's no closest date at all.");
            }
            stringJoiner.add(ldt.toString());
            list.forEach((s) -> stringJoiner.add(s.toString()));
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException("Your date is incorrect. Type the correct one.");
        }
        return stringJoiner.toString();
    }

    /**
     * Returns {@link String} with all orders
     *
     * @return {@link String} with all orders
     */
    @Override
    public String showAllOrders() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        orderDAO.getGadgetListWithKeys().entrySet().forEach((s) -> {
            stringJoiner.add(s.getKey().toString());
            s.getValue().forEach((s1) -> stringJoiner.add(s1.toString()));
        });
        return stringJoiner.toString();
    }

    /**
     * Returns all the orders in defined time
     *
     * @param first  min date
     * @param second max date
     * @return {@link List} with orders
     */
    @Override
    public List<Gadget> ordersInDefinedTime(String first, String second) throws IncorrectDateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        List<Gadget> list = new ArrayList<>();
        try {
            LocalDateTime firstDate = LocalDateTime.parse(first, formatter);
            LocalDateTime secondDate = LocalDateTime.parse(second, formatter);
            orderDAO.getGadgetListWithKeys().entrySet().forEach((s) -> {
                if (s.getKey().isAfter(firstDate) && s.getKey().isBefore(secondDate)) {
                    list.addAll(s.getValue());
                }
            });
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException(Messages.EXCEPTION_INCORRECT_DATE);
        }
        return list;
    }

    /**
     * Adds order to cache
     * Adds order to {@link OrderDAO}
     * Clears {@link BasketDAO}
     *
     * @param date date of the order
     * @return {@link String} price
     * @throws IncorrectDateException if date typed incorrectly
     */
    @Override
    public String makeOrder(String date) throws IncorrectDateException {
        List<Gadget> gadgets = new ArrayList<>();
        BigDecimal price = new BigDecimal(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        try {
            LocalDateTime parsed = LocalDateTime.parse(date, formatter);
            for (Gadget gadget : productDAO.getGadgetList()) {
                if (basketDAO.containsSerial(gadget.getSerialNumber())) {
                    cache.put(gadget.getSerialNumber(), basketDAO.getQuantityBySerialNumber(gadget.getSerialNumber()));
                    BigDecimal quantity = new BigDecimal(basketDAO.getQuantityBySerialNumber(gadget.getSerialNumber()));
                    price = price.add(gadget.getPrice().multiply(quantity));
                    gadgets.add(gadget);
                }
            }
            orderDAO.add(parsed, gadgets);
            basketDAO.clear();
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException("You date is incorrect. Type the correct one.");
        }
        return price.toString();
    }
}
