package com.epam.shop.commands;

import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;

/**
 * Command for interaction with LRU cache
 */
public class Show5LastProductsCommand implements ActionCommand {
    /**
     * ApplicationContext object
     */
    private ApplicationContext applicationContext;

    /**
     * Initial constructor
     * @param applicationContext storage
     */
    public Show5LastProductsCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Method for executing a command
     *
     * @return view object with a message
     */
    @Override
    public View execute() {
        return new View(applicationContext.getProductService().show5LastProducts());
    }

    /**
     * Returns string with a full command description
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Show 5 last gadgets";
    }
}
