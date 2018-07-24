package com.epam.shop.service;

import com.epam.shop.dao.BasketDAO;
import com.epam.shop.exception.NoSuchProductException;

public interface BasketService {

    /**
     * Returns {@link String} list of products from {@link BasketDAO}
     *
     * @return {@link String} list of products from {@link BasketDAO}
     */
    String showProductsInBasket();

    /**
     * Adds product to {@link BasketDAO} by its serial number
     *
     * @param serial serial number of the product
     * @throws NoSuchProductException if there's no such product
     */
    void addGadgetToBasketBySerial(String serial) throws NoSuchProductException;
}
