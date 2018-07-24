package com.epam.shop.server.handler;

import com.epam.shop.dto.ProductDTO;
import com.epam.shop.server.request.Request;
import com.epam.shop.server.response.Response;
import com.epam.shop.service.ProductService;

import java.io.PrintWriter;

public class CountHandler implements Handler {
    private ProductService productService;

    public CountHandler(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(Response response, PrintWriter printWriter, String... param) {
        ProductDTO productDTO = productService.getProductDTO();
        productDTO.setName(null);
        productDTO.setPrice(null);
        productDTO.setCount(productService.getGadgetList().size());
        response.write(productDTO, printWriter);
    }
}
