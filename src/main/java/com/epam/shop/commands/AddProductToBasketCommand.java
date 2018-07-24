package com.epam.shop.commands;

import com.epam.shop.exception.NoSuchProductException;
import com.epam.shop.messages.Messages;
import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;

/**
 * Command for adding an object of a gadget class
 * to BasketDAOImpl container
 */
public class AddProductToBasketCommand implements ActionCommand {
    /**
     * Object of a applicationContext class for getting
     * all the necessary components from it
     */
    private ApplicationContext applicationContext;

    /**
     * Initial constructor for initializing ApplicationContext object
     *
     * @param applicationContext object of a applicationContext class
     */
    public AddProductToBasketCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Method that executes action fro adding a gadget to a basket
     *
     * @return view object with a message
     * @throws NoSuchProductException if there's no such a gadget into the dao
     */
    @Override
    public View execute() throws NoSuchProductException {
        applicationContext.getWriter().writeLine(Messages.ADD_TO_BASKET);
        String serial = applicationContext.getReader().readLine();
        if (checksBackWord(serial)) {
            return new View(Messages.BACK_TO_MAIN);
        }
        applicationContext.getBasketService().addGadgetToBasketBySerial(serial);
        return new View("gadget " + serial + " was added to the basket");
    }

    /**
     * Returns string with a full command description
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Add gadget to basket";
    }
}
