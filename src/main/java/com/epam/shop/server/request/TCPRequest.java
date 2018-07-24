package com.epam.shop.server.request;

import com.epam.shop.exception.NoSuchProductException;
import com.epam.shop.messages.Messages;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TCPRequest implements Request {
    private final static String GET = "get_";
    private final static String EMPTY_STRING = "";
    private final static String REG_PARAM = "=.*";

    @Override
    public String getCommandName(String unparsedString) {
        Objects.requireNonNull(unparsedString);
        if (!unparsedString.contains("=")) {
            return unparsedString.replaceAll(GET, EMPTY_STRING).trim();
        }
        return unparsedString.replaceAll(GET, EMPTY_STRING).replaceAll(REG_PARAM, EMPTY_STRING).trim();

    }

    @Override
    public String getParams(String unparsedString) throws NoSuchProductException {
        Pattern pattern = Pattern.compile(REG_PARAM);
        Matcher matcher = pattern.matcher(unparsedString);
        if (matcher.find()) {
            return matcher.group().replaceAll("=", EMPTY_STRING).trim();
        }
        return null;
    }

}
