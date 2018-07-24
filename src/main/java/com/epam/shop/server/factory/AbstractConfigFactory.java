package com.epam.shop.server.factory;

import com.epam.shop.server.request.Request;
import com.epam.shop.server.response.Response;

public interface AbstractConfigFactory {
    Request getRequestInstance();
    Response getResponseInstance();
}
