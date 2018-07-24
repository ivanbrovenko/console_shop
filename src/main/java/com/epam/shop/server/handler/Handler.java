package com.epam.shop.server.handler;

import com.epam.shop.server.request.Request;
import com.epam.shop.server.response.Response;

import java.io.PrintWriter;

public interface Handler {
    void execute(Response response, PrintWriter printWriter, String... param);
}
