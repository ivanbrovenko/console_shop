package com.epam.shop.service;

import com.epam.shop.commands.ExitCommand;
import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.ProductDAO;
import com.epam.shop.exception.ApplicationTechnicalException;

import java.io.File;
import java.util.List;

/**
 * Writes products from {@link ProductDAO} to file on the {@link ExitCommand},
 * reads products from file and adds them to {@link ProductDAO} when application starts
 */
public interface PersistenceService {

    /**
     * Writes products from {@link ProductDAO} to file
     *
     *
     * @param productService {@link ProductService} to read products from
     * @param file           file write products to
     * @throws ApplicationTechnicalException if something wrong on the technical side
     */
    void writeFromProductDAO(ProductService productService, File file) throws ApplicationTechnicalException;

    /**
     * Reads products from file and adds them to {@link ProductDAO}
     *
     * @param file file to read product from
     * @return list of products
     * @throws ApplicationTechnicalException if something goes wrong on the technical side
     */
    List<Gadget> readFromProductDAO(File file) throws ApplicationTechnicalException;
}
