package com.epam.shop.commands;

import com.epam.shop.entity.Gadget;
import com.epam.shop.entity.MobileGadget;
import com.epam.shop.entity.Smartphone;
import com.epam.shop.exception.*;
import com.epam.shop.filler.Filler;
import com.epam.shop.filler.FillerContext;
import com.epam.shop.filler.impl.ReflectionFiller;
import com.epam.shop.reader.RandomGeneratorReader;
import com.epam.shop.reader.Reader;
import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;
import com.epam.shop.writer.impl.EmptyWriter;
import com.epam.shop.writer.Writer;

import java.util.HashMap;
import java.util.Map;

import static com.epam.shop.messages.Messages.*;

public class AddNewProductWithReflectionCommand implements ActionCommand {
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
    private Map<String, Gadget> productTypes;
    private Filler filler;

    /**
     * Initial constructor
     *
     * @param applicationContext storage
     */
    public AddNewProductWithReflectionCommand(ApplicationContext applicationContext) {
        filler = new ReflectionFiller();
        this.applicationContext = applicationContext;
        this.typeOfInput = new HashMap<>();
        this.productTypes = new HashMap<>();
        productTypes.put("1", new Gadget());
        productTypes.put("2", new MobileGadget());
        productTypes.put("3", new Smartphone());
        typeOfInput.put("1", new FillerContext(applicationContext.getReader(),applicationContext.getWriter()));
        typeOfInput.put("2", new FillerContext(new RandomGeneratorReader(),new EmptyWriter()));
    }

    /**
     * Executes the command
     *
     * @return {@link View}
     * @throws ApplicationTechnicalException if something goes wrong on the technical side
     * @throws NoSuchProductFactoryException if user typed wrong number
     * @throws NoSuchReaderException if user typed wrong number
     * @throws IncorrectPriceException       if user typed price in the incorrect format
     */
    @Override
    public View execute() throws NoSuchProductFactoryException, NoSuchReaderException, IncorrectPriceException, ApplicationTechnicalException, DuplicateSerialException {
        applicationContext.getProductService().setFillerContext(applicationContext,typeOfInput);
        addGadget(getConsoleWriter(),getConsoleReader());
        return new View(applicationContext.getStringFromMessageContainer(ADD_PRODUCT));
    }

    private void addGadget(Writer writer,Reader reader) throws NoSuchProductFactoryException, DuplicateSerialException, ApplicationTechnicalException {
        writer.writeLine(applicationContext.getStringFromMessageContainer(ADD_PRODUCT_CREATE_PRODUCT));
        String productType = read();
        if(!productTypes.containsKey(productType)){
            throw new NoSuchProductFactoryException();
        }
        Gadget gadget = productTypes.get(productType);
        filler.fill(getFillerReader(),getFillerWriter(),gadget);
        write(gadget.toString());
        applicationContext.getProductService().add(gadget);
    }

    private void write(String output){
        getConsoleWriter().writeLine(output);
    }

    private String read(){
        return getConsoleReader().readLine();
    }

    private Reader getFillerReader(){
        return applicationContext.getFillerContext().getReader();
    }

    private Writer getFillerWriter(){
       return applicationContext.getFillerContext().getWriter();
    }

    private Writer getConsoleWriter(){
        return applicationContext.getWriter();
    }

    private Reader getConsoleReader(){
        return applicationContext.getReader();
    }

    @Override
    public String toString() {
        return "Add new product to product list (reflection)";
    }
}