package com.epam.shop.service.impl;

import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.BasketDAO;
import com.epam.shop.dao.ProductDAO;
import com.epam.shop.exception.DAOLogicalException;
import com.epam.shop.exception.NoSuchProductException;
import com.epam.shop.messages.Messages;
import com.epam.shop.service.BasketService;
import org.apache.log4j.Logger;

import java.util.StringJoiner;


/**
 * Implementation of {@link BasketService}
 */
public class BasketServiceImpl implements BasketService {

    /**
     * Logger
     */
    private final static Logger logger = Logger.getRootLogger();
    /**
     * {@link BasketDAO}
     */
    private BasketDAO basketDAO;
    /**
     * {@link ProductDAO}
     */
    private ProductDAO productDAO;

    /**
     * Initial constructor
     * @param basketDAO {@link BasketDAO} argument to initialize
     * @param productDAO {@link ProductDAO} argument to initialize
     */
    public BasketServiceImpl(BasketDAO basketDAO,ProductDAO productDAO) {
        this.basketDAO = basketDAO;
        this.productDAO = productDAO;
    }

    /**
     * Adds product to {@link BasketDAO} by its serial number
     * @param serial serial number of the product
     * @throws NoSuchProductException if there's no such product
     */
    @Override
    public void addGadgetToBasketBySerial(String serial) throws NoSuchProductException {
        try {
            Gadget gadget = productDAO.getGadgetBySerial(serial);
            basketDAO.add(gadget);
        } catch (DAOLogicalException e) {
            throw new NoSuchProductException(Messages.EXCEPTION_NO_SUCH_GADGET);
        }
    }

    /**
     * Returns {@link String} with product list
     *
     * @return {@link String} with product list
     */
    public String showProductsInBasket() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        basketDAO.getGadgetListFromBasketWithKeys().entrySet().forEach((s) -> {
            try {
                stringJoiner.add("gadget: " + productDAO.getGadgetBySerial(s.getKey()).toString() + "\nquantity:" + s.getValue());
            } catch (DAOLogicalException e) {
                logger.error(e.getMessage());
            }
        });
        if (stringJoiner.length() < 1) {
            stringJoiner.add("The basket is empty.");
        }
        return stringJoiner.toString();
    }
}
