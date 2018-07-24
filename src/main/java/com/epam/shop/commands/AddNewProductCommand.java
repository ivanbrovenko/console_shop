package com.epam.shop.commands;


import com.epam.shop.entity.Gadget;
import com.epam.shop.exception.*;
import com.epam.shop.filler.FillerContext;
import com.epam.shop.reader.RandomGeneratorReader;
import com.epam.shop.reader.Reader;
import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.factory.Factory;
import com.epam.shop.factory.GadgetFactory;
import com.epam.shop.factory.MobileFactory;
import com.epam.shop.factory.SmartphoneFactory;
import com.epam.shop.view.View;
import com.epam.shop.writer.impl.EmptyWriter;
import com.epam.shop.writer.Writer;

import java.util.HashMap;
import java.util.Map;

import static com.epam.shop.messages.Messages.*;

public class AddNewProductCommand implements ActionCommand {
    /**
     * Storage
     */
    private ApplicationContext applicationContext;
    /**
     * Contains various version of input
     */
    private Map<String, FillerContext> typeOfInput;
    /**
     * Contains type of products to add
     */
    private Map<String, Factory> productFactories;

    public AddNewProductCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.typeOfInput = new HashMap<>();
        this.productFactories = new HashMap<>();
        productFactories.put("1", new GadgetFactory());
        productFactories.put("2", new MobileFactory());
        productFactories.put("3", new SmartphoneFactory());
        typeOfInput.put("1", new FillerContext(applicationContext.getReader(),applicationContext.getWriter()));
        typeOfInput.put("2", new FillerContext(new RandomGeneratorReader(),new EmptyWriter()));
    }

    @Override
    public View execute() throws NoSuchProductFactoryException, NoSuchReaderException, DuplicateSerialException {
        Reader reader = applicationContext.getReader();
        Writer writer = applicationContext.getWriter();
        applicationContext.getProductService().setFillerContext(applicationContext,typeOfInput);
        addGadgetToProductService(writer,reader);
        return new View(applicationContext.getStringFromMessageContainer(ADD_PRODUCT));
    }

    private void addGadgetToProductService(Writer writer,Reader reader) throws NoSuchProductFactoryException, DuplicateSerialException {
        Reader fillerReader = applicationContext.getFillerContext().getReader();
        Writer fillerWriter = applicationContext.getFillerContext().getWriter();
        writer.writeLine(applicationContext.getStringFromMessageContainer(ADD_PRODUCT_CREATE_PRODUCT));
        String productFactoryType = reader.readLine();
        if(!productFactories.containsKey(productFactoryType)){
            throw new NoSuchProductFactoryException();
        }
        Factory productFactory = productFactories.get(productFactoryType);
        Gadget gadget = productFactory.create(fillerReader,fillerWriter);
        writer.writeLine(gadget.toString());
        applicationContext.getProductService().add(gadget);
    }

    @Override
    public String toString() {
        return "Add new product to product list";
    }
}
