package com.epam.shop.factory;

import com.epam.shop.entity.Gadget;
import com.epam.shop.reader.Reader;
import com.epam.shop.writer.Writer;

public interface Factory {
    Gadget create(Reader reader, Writer writer);
}
