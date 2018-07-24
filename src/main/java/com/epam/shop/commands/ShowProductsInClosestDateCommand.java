package com.epam.shop.commands;

import com.epam.shop.exception.IncorrectDateException;
import com.epam.shop.exception.NoClosestDateException;
import com.epam.shop.messages.Messages;
import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;

/**
 * Command for showing order that was made in a date
 * closest to the date that user types
 */
public class ShowProductsInClosestDateCommand implements ActionCommand {
    /**
     * ApplicationContext object
     */
    private ApplicationContext applicationContext;

    public ShowProductsInClosestDateCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Method for executing a command
     *
     * @return view object with a message
     * @throws IncorrectDateException if date is incorrect
     */
    @Override
    public View execute() throws IncorrectDateException, NoClosestDateException{
        applicationContext.getWriter().writeLine(Messages.CLOSEST_DATE);
        String input = applicationContext.getReader().readLine();
        if (checksBackWord(input)) {
            return new View(Messages.BACK_TO_MAIN);
        }
        return new View(applicationContext.getOrderService().showGadgetInClosestDate(input));
    }

    /**
     * Returns string with a full command description
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Show gadgets in closest date";
    }
}
