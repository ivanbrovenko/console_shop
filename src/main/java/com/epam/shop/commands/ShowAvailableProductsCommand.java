package com.epam.shop.commands;

import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;

/**
 * Command for showing list of gadgets fro ProductDAOImpl
 */
public class ShowAvailableProductsCommand implements ActionCommand {
    /**
     * ApplicationContext object
     */
    private ApplicationContext applicationContext;

    /**
     * Initial constructor
     *
     * @param applicationContext applicationContext object
     */
    public ShowAvailableProductsCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Method fro executing a command
     *
     * @return view object with a message
     */
    @Override
    public View execute() {
        return new View(applicationContext.getProductService().showAvailableGadgets());
    }

    /**
     * Returns string with a full command description
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Show available gadgets";
    }
}
