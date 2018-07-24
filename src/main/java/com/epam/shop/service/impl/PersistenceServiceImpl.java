package com.epam.shop.service.impl;

import com.epam.shop.entity.Gadget;
import com.epam.shop.entity.Smartphone;
import com.epam.shop.dao.ProductDAO;
import com.epam.shop.exception.ApplicationTechnicalException;
import com.epam.shop.service.PersistenceService;
import com.epam.shop.service.ProductService;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link PersistenceService}
 */
public class PersistenceServiceImpl implements PersistenceService {

    /**
     * Writes products from {@link ProductDAO} to file
     *
     * @param productService {@link ProductService} to read products from
     * @param file           file write products to
     * @throws ApplicationTechnicalException
     */
    @Override
    public void writeFromProductDAO(ProductService productService, File file) throws ApplicationTechnicalException {
        FileOutputStream fileOutputStream = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            ArrayList<Gadget> list = (ArrayList<Gadget>) productService.getGadgetList();
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new ApplicationTechnicalException();
        } catch (IOException e) {
            throw new ApplicationTechnicalException();
        }
    }

    /**
     * Reads products from file and adds them to {@link ProductDAO}
     *
     * @param file file to read product from
     * @return list of products
     * @throws ApplicationTechnicalException
     */
    @Override
    public List<Gadget> readFromProductDAO(File file) throws ApplicationTechnicalException {
        ArrayList<Gadget> list = new ArrayList<>();
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                list = (ArrayList<Gadget>) objectInputStream.readObject();
                objectInputStream.close();
            } catch (FileNotFoundException e) {
                throw new ApplicationTechnicalException();
            } catch (IOException e) {
                throw new ApplicationTechnicalException();
            } catch (ClassNotFoundException e) {
                throw new ApplicationTechnicalException();
            }
            return list;
        }
        fillProductList(list);
        return list;
    }

    /**
     * Fills product list with random products
     * @param list {@link List} to fill
     */
    private void fillProductList(List<Gadget> list) {
        for (int i = 1; i < 9; i++) {
            list.add(new Smartphone(111 * i + "", "firm" + i, new BigDecimal(111 * i), "model" + i, "3.5", "3.5db"));
        }
    }
}
