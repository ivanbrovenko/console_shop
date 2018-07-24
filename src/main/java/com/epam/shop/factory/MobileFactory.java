package com.epam.shop.factory;

import com.epam.shop.entity.MobileGadget;
import com.epam.shop.reader.Reader;
import com.epam.shop.writer.Writer;

import static com.epam.shop.messages.Messages.*;

public class MobileFactory implements Factory {

    @Override
    public MobileGadget create(Reader reader, Writer writer){
        MobileGadget mobile = new MobileGadget();
        writer.writeLine(TYPE_SERIAL);
        mobile.setSerialNumber(reader.readLine());
        writer.writeLine(TYPE_FIRM);
        mobile.setFirm(reader.readLine());
        writer.writeLine(TYPE_MODEL);
        mobile.setModel(reader.readLine());
        writer.writeLine(TYPE_PRICE);
        mobile.setPrice(reader.readBigDecimal());
        writer.writeLine(TYPE_BATTERY_CAPACITY);
        mobile.setDiagonal(reader.readLine());
        return mobile;
    }
}
