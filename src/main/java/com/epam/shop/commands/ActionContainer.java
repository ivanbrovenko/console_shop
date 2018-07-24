package com.epam.shop.commands;

import com.epam.shop.storage.ApplicationContext;

import java.util.*;

/**
 * Class that initializes all the commands
 */
public class ActionContainer {
    /**
     * Map that contains command and its key number
     */
    private Map<String, ActionCommand> commands;

    /**
     * Initial constructor
     *
     * @param applicationContext object of ApplicationContext class
     */
    public ActionContainer(ApplicationContext applicationContext) {
        commands = new LinkedHashMap<>();
        commands.put("1", new ShowAvailableProductsCommand(applicationContext));
        commands.put("2", new AddProductToBasketCommand(applicationContext));
        commands.put("3", new MakeOrderCommand(applicationContext));
        commands.put("4", new ShowProductsInBasketCommand(applicationContext));
        commands.put("5", new Show5LastProductsCommand(applicationContext));
        commands.put("6", new ShowAllOrdersCommand(applicationContext));
        commands.put("7", new ShowProductsInTimeIntervalCommand(applicationContext));
        commands.put("8", new ShowProductsInClosestDateCommand(applicationContext));
        commands.put("9", new AddNewProductCommand(applicationContext));
        commands.put("10",new AddNewProductWithReflectionCommand(applicationContext));
        commands.put("0",new ExitCommand(applicationContext));
    }

    /**
     * Method for getting a certain command
     *
     * @param action key for getting a certain command
     * @return {@link ActionCommand}
     */
    public ActionCommand getCommand(String action) {
        return commands.get(action);
    }

    /**
     * Returns unmodifiable command container
     *
     * @return {@link Map} that contains commands and their numbers
     */
    public Map<String, ActionCommand> getCommands() {
        return Collections.unmodifiableMap(commands);
    }
}
