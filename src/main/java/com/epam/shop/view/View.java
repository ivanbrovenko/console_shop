package com.epam.shop.view;

import com.epam.shop.commands.ActionContainer;
import com.epam.shop.writer.impl.ConsoleWriter;

import java.util.StringJoiner;

/**
 * Prints user message to console
 */
public class View {
    /**
     * Command container
     */
    ActionContainer actionContainer;

    /**
     * Prints message
     *
     * @param message message
     */
    public View(String message) {
        new ConsoleWriter(System.out).writeLine("Request result:\n" + message);
    }

    /**
     * Initial constructor
     *
     * @param actionContainer action factory to get command numbers from
     */
    public View(ActionContainer actionContainer) {
        this.actionContainer = actionContainer;
    }

    /**
     * Method that returns all the command list
     * and additional instruction for console
     *
     * @return {@link String}  instruction
     */
    public String getInstruction() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("\nType number of a certain command");
        actionContainer.getCommands().entrySet().stream().forEach((s) -> stringJoiner.add(s.getKey() + ". " + s.getValue()));
        stringJoiner.add("==================================");
        return stringJoiner.toString();
    }
}
