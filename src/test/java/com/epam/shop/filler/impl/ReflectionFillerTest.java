package com.epam.shop.filler.impl;

import com.epam.shop.entity.Gadget;
import com.epam.shop.reader.RandomGeneratorReader;
import com.epam.shop.reader.Reader;
import com.epam.shop.writer.Writer;
import com.epam.shop.writer.impl.ConsoleWriter;
import com.epam.shop.writer.impl.EmptyWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ReflectionFillerTest {
    private ReflectionFiller reflectionFiller;
    private Writer writer;
    private Reader reader;
    private Gadget gadget;

    @Before
    public void setUp() throws Exception {
        writer = new EmptyWriter();
        reflectionFiller = new ReflectionFiller();
        reader = new RandomGeneratorReader();
        gadget = new Gadget();
        reflectionFiller.fill(reader,writer,gadget);
    }

    @Test
    public void testFillMethodOnAddingCorrectNumbers(){
        assertNotEquals(gadget.getPrice(),gadget.getSerialNumber());
        assertNotEquals(gadget.getSerialNumber(),gadget.getFirm());
        assertNotEquals(gadget.getModel(),gadget.getPrice());
    }

    @Test
    public void testGeneratedValuesOnNotNull(){
        assertNotNull(gadget.getSerialNumber());
        assertNotNull(gadget.getModel());
        assertNotNull(gadget.getPrice());
        assertNotNull(gadget.getFirm());
    }
}