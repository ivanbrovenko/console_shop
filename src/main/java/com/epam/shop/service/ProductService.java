package com.epam.shop.service;

import com.epam.shop.dto.ProductDTO;
import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.ProductDAO;
import com.epam.shop.exception.DAOLogicalException;
import com.epam.shop.cache.LRUCache;
import com.epam.shop.exception.DuplicateSerialException;
import com.epam.shop.exception.NoSuchReaderException;
import com.epam.shop.filler.FillerContext;
import com.epam.shop.storage.ApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Service to work with {@link ProductDAO}
 */
public interface ProductService {

    /**
     * Returns product by its serial
     *
     * @param serial serial number
     * @return {@link Gadget}
     * @throws DAOLogicalException if something happens in the dao layer
     */
    Gadget getGadgetBySerial(String serial) throws DAOLogicalException;

    /**
     * Adds product to {@link ProductDAO}
     *
     * @param gadget {@link Gadget}
     * @return <tt>true</tt>
     */
    boolean add(Gadget gadget) throws DuplicateSerialException;

    /**
     * Returns {@link String} with list of available gadgets
     *
     * @return {@link String} with list of available gadgets
     */
    String showAvailableGadgets();

    /**
     * Returns {@link String} with products from {@link LRUCache}
     *
     * @return {@link String} with products from {@link LRUCache}
     */
    String show5LastProducts();

    List<Gadget> getGadgetList();
    void setFillerContext(ApplicationContext applicationContext, Map<String,FillerContext> typeOfInput) throws NoSuchReaderException;
    ProductDTO getProductDTO();
}