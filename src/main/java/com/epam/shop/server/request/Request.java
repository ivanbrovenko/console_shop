package com.epam.shop.server.request;

import com.epam.shop.exception.NoSuchProductException;

public interface Request {
    String getCommandName(String unparsedString);
    String getParams(String unparsedString) throws NoSuchProductException;
}
