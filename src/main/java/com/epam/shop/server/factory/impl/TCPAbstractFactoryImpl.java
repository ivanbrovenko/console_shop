package com.epam.shop.server.factory.impl;

import com.epam.shop.server.factory.AbstractConfigFactory;
import com.epam.shop.server.request.Request;
import com.epam.shop.server.request.TCPRequest;
import com.epam.shop.server.response.Response;
import com.epam.shop.server.response.TCPResponse;

public class TCPAbstractFactoryImpl implements AbstractConfigFactory {
    @Override
    public Request getRequestInstance() {
        return new TCPRequest();
    }

    @Override
    public Response getResponseInstance() {
        return new TCPResponse();
    }
}
