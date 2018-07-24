package com.epam.shop.factory;

import com.epam.shop.entity.Gadget;
import com.epam.shop.reader.Reader;
import com.epam.shop.writer.Writer;

import static com.epam.shop.messages.Messages.*;


public class GadgetFactory implements Factory{

    @Override
    public Gadget create(Reader reader, Writer writer){
        Gadget gadget = new Gadget();
        writer.writeLine(TYPE_SERIAL);
        gadget.setSerialNumber(reader.readLine());
        writer.writeLine(TYPE_FIRM);
        gadget.setFirm(reader.readLine());
        writer.writeLine(TYPE_MODEL);
        gadget.setModel(reader.readLine());
        writer.writeLine(TYPE_PRICE);
        gadget.setPrice(reader.readBigDecimal());
        return gadget;
    }
}
