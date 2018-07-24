package com.epam.shop.server.factory.impl;

import com.epam.shop.server.factory.AbstractConfigFactory;
import com.epam.shop.server.request.HTTPRequest;
import com.epam.shop.server.request.Request;
import com.epam.shop.server.response.HTTPResponse;
import com.epam.shop.server.response.Response;

public class HTTPAbstractFactoryImpl implements AbstractConfigFactory {
    @Override
    public Request getRequestInstance() {
        return new HTTPRequest();
    }

    @Override
    public Response getResponseInstance() {
        return new HTTPResponse();
    }
}
