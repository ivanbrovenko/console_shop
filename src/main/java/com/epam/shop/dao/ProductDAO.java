package com.epam.shop.dao;

import com.epam.shop.entity.Gadget;
import com.epam.shop.exception.DAOLogicalException;

import java.util.List;

/**
 * ProductDAO
 */
public interface ProductDAO {

    /**
     * Returns list of gadgets
     *
     * @return list of gadgets
     */
    List<Gadget> getGadgetList();

    /**
     * Adds new gadget
     *
     * @param gadget {@link Gadget} product
     * @return <tt>true</tt>
     */
    boolean add(Gadget gadget) throws DAOLogicalException;

    /**
     * Returns gadget object with a specified serial
     *
     * @param serial serial number
     * @return gadget object with a specified serial
     * @throws DAOLogicalException if something will go wrong
     */
    Gadget getGadgetBySerial(String serial) throws DAOLogicalException;
}
