package com.epam.shop.filler;

import com.epam.shop.entity.Gadget;
import com.epam.shop.exception.ApplicationTechnicalException;
import com.epam.shop.reader.Reader;
import com.epam.shop.writer.Writer;

public interface Filler {
    void fill(Reader reader, Writer writer, Gadget gadget) throws ApplicationTechnicalException;
}