package com.epam.shop.commands;

import com.epam.shop.exception.ApplicationTechnicalException;
import com.epam.shop.service.PersistenceService;
import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;

import java.io.File;

/**
 * Exits from the application
 */
public class ExitCommand implements ActionCommand {
    /**
     * Storage
     */
    private ApplicationContext applicationContext;

    /**
     * Initial constructor
     *
     * @param applicationContext storage
     */
    public ExitCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Executes the command
     *
     * @return empty {@link View}
     * @throws ApplicationTechnicalException if something goes wrong on technical side
     */
    @Override
    public View execute() throws ApplicationTechnicalException {
        PersistenceService persistenceService = applicationContext.getPersistenceService();
        persistenceService.writeFromProductDAO(applicationContext.getProductService(), applicationContext.getFile());
        System.exit(-1);
        return new View("");
    }

    /**
     * Returns string type of {@link ExitCommand}
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Exit";
    }
}
