package com.epam.shop.commands;

import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;

/**
 * Command for getting all the gadgets from a basket
 */
public class ShowProductsInBasketCommand implements ActionCommand {
    /**
     * ApplicationContext object
     */
    private ApplicationContext applicationContext;

    /**
     * Initial constructor
     *
     * @param applicationContext applicationContext object
     */
    public ShowProductsInBasketCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Method fro executing a command
     *
     * @return view object with a message
     */
    @Override
    public View execute() {
        return new View(applicationContext.getBasketService().showProductsInBasket());
    }

    /**
     * Returns string with a full command description
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Show gadgets in basket";
    }
}
