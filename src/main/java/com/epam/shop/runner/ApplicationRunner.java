package com.epam.shop.runner;

import com.epam.shop.controller.Controller;
import com.epam.shop.exception.*;
import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.view.View;
import org.apache.log4j.Logger;

import java.util.Locale;


/**
 * Class for running application
 */
public class ApplicationRunner {

    /**
     * Logger
     */
    Logger logger = Logger.getRootLogger();

    /**
     * Fills productDAO with a list of products
     * Working with user input
     */
    public void run() {
        ApplicationContext applicationContext = null;

        Locale.setDefault(new Locale("en", "US", "US"));
        try {
            applicationContext = new ApplicationContext();
        } catch (ApplicationTechnicalException e) {
            logger.error(e.getMessage());
        } catch (DuplicateSerialException e) {
            logger.error(e.getMessage());
        }
        Controller controller = new Controller(applicationContext);
        processRequest(applicationContext, controller);
    }

    private void processRequest(ApplicationContext applicationContext, Controller controller) {
        applicationContext.getWriter().writeLine(new View(controller.getFactory()).getInstruction());
        while (applicationContext.getReader().hasNextLine()) {
            String userInput = applicationContext.getReader().readLine();
            try {
                controller.processRequest(userInput);
            } catch (NoSuchCommandException | NoClosestDateException | IncorrectDateException | NoSuchProductException |
                    NoSuchReaderException | NoSuchProductFactoryException |DuplicateSerialException| IncorrectPriceException e) {
                applicationContext.getWriter().writeLine(e.getMessage());
            } catch (ApplicationTechnicalException e) {
                logger.error(e.getMessage());
            }
            applicationContext.getWriter().writeLine(new View(controller.getFactory()).getInstruction());
        }
    }
}
