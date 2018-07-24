package com.epam.shop.server.handler;

import com.epam.shop.dto.ProductDTO;
import com.epam.shop.entity.Gadget;
import com.epam.shop.exception.DAOLogicalException;
import com.epam.shop.exception.NoSuchProductException;
import com.epam.shop.messages.Messages;
import com.epam.shop.server.request.Request;
import com.epam.shop.server.response.Response;
import com.epam.shop.service.ProductService;

import java.io.PrintWriter;

import static com.epam.shop.messages.Messages.EXCEPTION_NO_SUCH_GADGET;


public class ItemHandler implements Handler {
    private ProductService productService;

    public ItemHandler(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute(Response response, PrintWriter printWriter, String... param) {
        try {
            checkParamForValidValue(param[0]);
            ProductDTO productDTO = productService.getProductDTO();
            Gadget gadget = productService.getGadgetBySerial(param[0]);
            productDTO.setName(gadget.getModel());
            productDTO.setPrice(gadget.getPrice());
            response.write(productDTO, printWriter);
        } catch (DAOLogicalException | NoSuchProductException e) {
            response.write(e.getMessage(), printWriter);
        }
    }

    private void checkParamForValidValue(String param) throws NoSuchProductException {
        if (param == null || param.equals("")) {
            throw new NoSuchProductException(EXCEPTION_NO_SUCH_GADGET);
        }
    }
}
