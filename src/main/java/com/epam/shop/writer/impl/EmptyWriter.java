package com.epam.shop.writer.impl;

import com.epam.shop.writer.Writer;

public class EmptyWriter implements Writer {
    @Override
    public void writeLine(String line) {
        //NOP
    }
}
