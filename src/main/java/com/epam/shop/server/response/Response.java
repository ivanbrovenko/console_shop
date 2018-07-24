package com.epam.shop.server.response;

import com.epam.shop.dto.ProductDTO;

import java.io.PrintWriter;

public interface Response {
    void write(ProductDTO productDTO, PrintWriter printWriter);

    void write(String message, PrintWriter p);
}
