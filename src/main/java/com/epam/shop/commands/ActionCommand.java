package com.epam.shop.commands;

import com.epam.shop.exception.*;

import com.epam.shop.view.View;

import java.util.Objects;

/**
 * Interface for executing commands
 */
public interface ActionCommand {
    View execute() throws NoSuchProductException, IncorrectDateException, NoClosestDateException, ApplicationTechnicalException, NoSuchProductFactoryException, NoSuchReaderException, IncorrectPriceException, DuplicateSerialException;

    default boolean checksBackWord(String param){
        Objects.requireNonNull(param);
        return param.equals("back");
    }
}
