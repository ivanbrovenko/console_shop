package com.epam.shop.server.response;

import com.epam.shop.dto.ProductDTO;
import com.epam.shop.server.json.JsonWriter;

import java.io.PrintWriter;

public class HTTPResponse implements Response {
    private final static String HEADER = "HTTP/1.1 200 OK\nContent-Type: text/html\n\r\n";
    private final static String P_OPEN = "<p>";
    private final static String P_CLOSE = "</p>";

    @Override
    public void write(ProductDTO productDTO, PrintWriter printWriter) {
        writeInHTTPFormat(new JsonWriter().toJson(productDTO), printWriter);
    }

    @Override
    public void write(String message, PrintWriter printWriter) {
        writeInHTTPFormat(message, printWriter);
    }

    private void writeInHTTPFormat(String message, PrintWriter printWriter) {
        printWriter.println(HEADER);
        printWriter.println(P_OPEN + message + P_CLOSE);
        printWriter.flush();
    }
}
