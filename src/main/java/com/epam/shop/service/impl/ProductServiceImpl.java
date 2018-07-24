package com.epam.shop.service.impl;

import com.epam.shop.dto.ProductDTO;
import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.ProductDAO;
import com.epam.shop.exception.DAOLogicalException;
import com.epam.shop.cache.LRUCache;
import com.epam.shop.exception.DuplicateSerialException;
import com.epam.shop.exception.NoSuchReaderException;
import com.epam.shop.filler.FillerContext;
import com.epam.shop.messages.Messages;
import com.epam.shop.service.ProductService;
import com.epam.shop.storage.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static com.epam.shop.messages.Messages.ADD_PRODUCT_WAY_OF_FILLING;

/**
 * Implementation of {@link ProductService}
 */
public class ProductServiceImpl implements ProductService {
    /**
     * DAO to work with
     */
    private ProductDAO productDAO;
    /**
     * Cache to store elements in
     */
    private LRUCache cache;

    /**
     * Initial constructor
     *
     * @param productDAO {@link ProductDAO} argument
     * @param cache      {@link LRUCache} argument
     */
    public ProductServiceImpl(ProductDAO productDAO, LRUCache cache) {
        this.productDAO = productDAO;
        this.cache = cache;
    }

    /**
     * Returns product by serial if its available
     *
     * @param serial serial number of a product
     * @return {@link Gadget}
     * @throws DAOLogicalException if something happens in DAO layer
     */
    @Override
    public Gadget getGadgetBySerial(String serial) throws DAOLogicalException {
        return productDAO.getGadgetBySerial(serial);
    }

    /**
     * Adds gadget to {@link ProductDAO}
     *
     * @param gadget argument to add
     * @return <tt>true</tt>
     */
    @Override
    public boolean add(Gadget gadget) throws DuplicateSerialException {

        try {
            return productDAO.add(gadget);
        } catch (DAOLogicalException e) {
            throw new DuplicateSerialException(e.getMessage());
        }
    }

    /**
     * Returns {@link String} with list of available gadgets
     *
     * @return {@link String} with list of available gadgets
     */
    @Override
    public String showAvailableGadgets() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        productDAO.getGadgetList().forEach((s) -> stringJoiner.add(s.toString()));
        return stringJoiner.toString();
    }

    /**
     * Returns {@link String} with products from {@link LRUCache}
     *
     * @return {@link String} with products from {@link LRUCache}
     */
    @Override
    public String show5LastProducts() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        cache.entrySet().forEach((s) -> {
            try {
                stringJoiner.add("gadget: " + productDAO.getGadgetBySerial(s.getKey()).toString() + "\nquantity:" + s.getValue());
            } catch (DAOLogicalException e) {
                e.printStackTrace();
            }
        });
        if (stringJoiner.length() < 1) {
            stringJoiner.add(Messages.EMPTY_CACHE);
        }
        return stringJoiner.toString();
    }

    @Override
    public List<Gadget> getGadgetList() {
        return productDAO.getGadgetList();
    }

    @Override
    public void setFillerContext(ApplicationContext applicationContext, Map<String, FillerContext> typeOfInput) throws NoSuchReaderException {
        if(applicationContext.getFillerContext() == null){
            applicationContext.getWriter().writeLine(applicationContext.getStringFromMessageContainer(ADD_PRODUCT_WAY_OF_FILLING));
            String fillerType = applicationContext.getReader().readLine();
            if(!typeOfInput.containsKey(fillerType)){
                throw new NoSuchReaderException();
            }
            FillerContext fillerContext = typeOfInput.get(fillerType);
            applicationContext.setFillerContext(fillerContext);
        };
    }

    @Override
    public ProductDTO getProductDTO(){
        return new ProductDTO();
    }

}