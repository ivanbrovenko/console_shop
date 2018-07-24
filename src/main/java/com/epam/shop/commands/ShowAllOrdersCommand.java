package com.epam.shop.commands;

import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;

/**
 * Command for showing all the orders
 */
public class ShowAllOrdersCommand implements ActionCommand {
    /**
     * ApplicationContext object
     */
    private ApplicationContext applicationContext;

    /**
     * Initial constructor
     *
     * @param applicationContext applicationContext object
     */
    public ShowAllOrdersCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Method for executing a command
     *
     * @return view object with a message
     */
    @Override
    public View execute() {
        return new View(applicationContext.getOrderService().showAllOrders());
    }

    /**
     * Returns string with a full command description
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Show all orders";
    }
}
