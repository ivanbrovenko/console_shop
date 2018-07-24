package com.epam.shop.server.request;

import com.epam.shop.exception.NoSuchProductException;
import com.epam.shop.messages.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPRequest implements Request {
    private final static String GET_SHOP = "GET /shop/";
    private final static String HTTP = " HTTP/1.1";
    private final static String EMPTY_STRING = "";
    private final static String REG_FOR_STRING_AFTER_NAME = "\\?.*";
    private final static String EQUALS = "=";
    private final static String REG_FOR_PARAMS = "=.*";

    @Override
    public String getCommandName(String unparsedString) {
        if (!unparsedString.contains("=")){
            return unparsedString.replaceAll(GET_SHOP,EMPTY_STRING).replaceAll(HTTP,EMPTY_STRING).trim();
        }
        return unparsedString.replaceAll(GET_SHOP,EMPTY_STRING).replaceAll(REG_FOR_STRING_AFTER_NAME,EMPTY_STRING).trim();
    }

    @Override
    public String getParams(String unparsedString) throws NoSuchProductException {
        Pattern pattern = Pattern.compile(REG_FOR_PARAMS);
        Matcher matcher = pattern.matcher(unparsedString);
        if (matcher.find()){
            return matcher.group().replaceAll(EQUALS,EMPTY_STRING).replaceAll(HTTP,EMPTY_STRING).trim();
        }
        return null;
    }

}
