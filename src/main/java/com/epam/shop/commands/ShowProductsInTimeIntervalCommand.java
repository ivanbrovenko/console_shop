package com.epam.shop.commands;

import com.epam.shop.exception.IncorrectDateException;
import com.epam.shop.messages.Messages;
import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Command for showing list of orders in certain interval
 */
public class ShowProductsInTimeIntervalCommand implements ActionCommand {
    /**
     * ApplicationContext object
     */
    private ApplicationContext applicationContext;

    /**
     * Initial constructor
     *
     * @param applicationContext applicationContext object
     */
    public ShowProductsInTimeIntervalCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Method for executing a command
     *
     * @return view object with a message
     * @throws IncorrectDateException if user entered date in incorrect format
     */
    @Override
    public View execute() throws IncorrectDateException {
        LocalDateTime firstDate;
        LocalDateTime secondDate;
        applicationContext.getWriter().writeLine(Messages.TIME_INTERVAL_FIRST_DATE);
        String first = applicationContext.getReader().readLine();
        if (checksBackWord(first)) {
            return new View(Messages.BACK_TO_MAIN);
        }
        applicationContext.getWriter().writeLine(Messages.TIME_INTERVAL_SECOND_DATE);
        String second = applicationContext.getReader().readLine();
        if (checksBackWord(second)) {
            return new View(Messages.BACK_TO_MAIN);
        }
        StringJoiner stringJoiner = new StringJoiner("\n");
        applicationContext.getOrderService().ordersInDefinedTime(first, second).forEach((s) -> {
            stringJoiner.add(s.toString());
        });
        if (stringJoiner.length() < 1) {
            stringJoiner.add(Messages.NO_ORDER_IN_INTERVAL);
        }
        return new View(stringJoiner.toString());
    }

    /**
     * Returns string with a full command description
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Show gadgets in time interval";
    }
}
