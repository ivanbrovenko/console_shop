package com.epam.shop.server.response;

import com.epam.shop.dto.ProductDTO;

import java.io.PrintWriter;

public class TCPResponse implements Response {

    public void write(ProductDTO productDTO, PrintWriter printWriter) {
        writeInTCPFormat(productDTO.toString(), printWriter);
    }

    @Override
    public void write(String message, PrintWriter printWriter) {
        writeInTCPFormat(message, printWriter);
    }

    private void writeInTCPFormat(String message, PrintWriter printWriter) {
        printWriter.write(message);
        printWriter.flush();
    }

}
