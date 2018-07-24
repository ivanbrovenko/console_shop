package com.epam.shop.server.handler.container;

import com.epam.shop.server.handler.CountHandler;
import com.epam.shop.server.handler.ItemHandler;
import com.epam.shop.server.handler.Handler;
import com.epam.shop.service.ProductService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HandlerContainer {
    private ProductService productService;
    private Map<String, Handler> handlerContainer;

    public HandlerContainer(ProductService productService) {
        this.productService = productService;
        this.handlerContainer = new HashMap<>();
        handlerContainer.put("count", new CountHandler(productService));
        handlerContainer.put("item", new ItemHandler(productService));
    }

    public Map<String, Handler> getHandlerContainer() {
        return Collections.unmodifiableMap(handlerContainer);
    }
}
