package com.epam.shop.factory;

import com.epam.shop.entity.Smartphone;
import com.epam.shop.reader.Reader;
import com.epam.shop.writer.Writer;

import static com.epam.shop.messages.Messages.*;


public class SmartphoneFactory implements Factory {

    @Override
    public Smartphone create(Reader reader, Writer writer){
        Smartphone smartphone = new Smartphone();
        writer.writeLine(TYPE_SERIAL);
        smartphone.setSerialNumber(reader.readLine());
        writer.writeLine(TYPE_FIRM);
        smartphone.setFirm(reader.readLine());
        writer.writeLine(TYPE_MODEL);
        smartphone.setModel(reader.readLine());
        writer.writeLine(TYPE_PRICE);
        smartphone.setPrice(reader.readBigDecimal());
        writer.writeLine(TYPE_BATTERY_CAPACITY);
        smartphone.setDiagonal(reader.readLine());
        writer.writeLine(TYPE_FRONTAL_SPEAKER_VOLUME);
        smartphone.setFrontalSpeakerVolume(reader.readLine());
        return smartphone;
    }
}
