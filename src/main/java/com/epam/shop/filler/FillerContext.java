package com.epam.shop.filler;

import com.epam.shop.reader.Reader;
import com.epam.shop.writer.Writer;

public class FillerContext {
    private Reader reader;
    private Writer writer;

    public FillerContext(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
