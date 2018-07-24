package com.epam.shop.commands;

import com.epam.shop.exception.IncorrectDateException;
import com.epam.shop.messages.Messages;
import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;

/**
 * Command for making an order
 */
public class MakeOrderCommand implements ActionCommand {
    /**
     * ApplicationContext object
     */
    private ApplicationContext applicationContext;

    /**
     * Initial constructor
     *
     * @param applicationContext applicationContext object
     */
    public MakeOrderCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Method for execution a command
     *
     * @return view object with a message
     * @throws IncorrectDateException in user typed a wrong date
     */
    @Override
    public View execute() throws IncorrectDateException {
        applicationContext.getWriter().writeLine(Messages.MAKE_ORDER);
        String date = applicationContext.getReader().readLine();
        if (checksBackWord(date)) {
            return new View(Messages.BACK_TO_MAIN);
        }
        return new View("Thanks for your purchasing.\nFinal price is: " + applicationContext.getOrderService().makeOrder(date) + "$");
    }

    /**
     * Returns string with a full command description
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Make an order";
    }
}
