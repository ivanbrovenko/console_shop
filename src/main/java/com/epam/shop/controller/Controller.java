package com.epam.shop.controller;

import com.epam.shop.commands.ActionCommand;
import com.epam.shop.commands.ActionContainer;
import com.epam.shop.exception.*;
import com.epam.shop.messages.Messages;
import com.epam.shop.storage.ApplicationContext;

/**
 * Controller
 */
public class Controller {
    /**
     * Command container
     */
    private ActionContainer factory;

    /**
     * Initial constructor
     *
     * @param applicationContext applicationContext object
     */
    public Controller(ApplicationContext applicationContext) {
        this.factory = new ActionContainer(applicationContext);
    }

    /**
     * Method for interaction with commands
     *
     * @param action key word of a certain command
     * @throws NoSuchCommandException if there's no command with such a key word
     * @throws NoSuchProductException if there's no such object
     * @throws IncorrectDateException if user types date in incorrect format
     * @throws NoClosestDateException if there's no closest date at all
     */
    public void processRequest(String action) throws NoSuchCommandException, NoSuchProductException, IncorrectDateException, NoClosestDateException, ApplicationTechnicalException, NoSuchReaderException, NoSuchProductFactoryException, IncorrectPriceException, DuplicateSerialException {
        ActionCommand command = factory.getCommand(action);
        if (command != null) {
            command.execute();
        } else {
            throw new NoSuchCommandException(Messages.EXCEPTION_NO_SUCH_COMMAND);
        }
    }

    /**
     * Method for getting action container
     *
     * @return action container
     */
    public ActionContainer getFactory() {
        return factory;
    }
}
