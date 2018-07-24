package com.epam.shop.dao.impl;

import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.ProductDAO;
import com.epam.shop.exception.DAOLogicalException;
import com.epam.shop.messages.Messages;

import java.util.ArrayList;
import java.util.List;

import static com.epam.shop.messages.Messages.EXCEPTION_NO_SUCH_GADGET;

/**
 * DAO implementation that contains products
 */
public class ProductDAOImpl implements ProductDAO {
    /**
     * Container that contains list of gadgets
     */
    private List<Gadget> list;

    /**
     * Initial constructor
     */
    public ProductDAOImpl() {
        this.list = new ArrayList<>();
    }

    /**
     * Method for adding a new gadget to container
     *
     * @param gadget object of gadget
     * @return boolean value
     */
    @Override
    public boolean add(Gadget gadget) throws DAOLogicalException {
        for(Gadget tempGadget:list){
            if(tempGadget.getSerialNumber().equals(gadget.getSerialNumber())){
                throw new DAOLogicalException(Messages.DUPLICATE_SERIAL_EXCEPTION);
            }
        }
        return list.add(gadget);
    }

    /**
     * Getter for inner container
     *
     * @return list of gadgets
     */
    @Override
    public List<Gadget> getGadgetList() {
        return list;
    }

    /**
     * Method for getting gadget by serial
     *
     * @param serial serial number
     * @return gadget with a certain serial
     * @throws DAOLogicalException if serial number is null
     */
    @Override
    public Gadget getGadgetBySerial(String serial) throws DAOLogicalException {
        if (serial != null) {
            for (Gadget gadget : list) {
                if (gadget.getSerialNumber().equals(serial)) {
                    return gadget;
                }
            }
        }
        throw new DAOLogicalException(EXCEPTION_NO_SUCH_GADGET);
    }
}
