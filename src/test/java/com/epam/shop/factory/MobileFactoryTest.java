package com.epam.shop.factory;

import com.epam.shop.entity.Gadget;
import com.epam.shop.entity.MobileGadget;
import com.epam.shop.entity.Smartphone;
import com.epam.shop.reader.Reader;
import com.epam.shop.writer.Writer;
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

@RunWith(MockitoJUnitRunner.class)
public class MobileFactoryTest {
    private MobileFactory mobileFactory;
    private Writer writer;
    private final static String LINE_STUB = "stub";
    private final static BigDecimal BIG_DECIMAL_STUB = new BigDecimal(666);

    @Mock
    private Reader reader;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(reader.readLine()).thenReturn(LINE_STUB);
        Mockito.when(reader.readBigDecimal()).thenReturn(BIG_DECIMAL_STUB);
        mobileFactory = new MobileFactory();
        writer = new EmptyWriter();
    }

    @Test
    public void testOnCreatingGadgetWithProperValues(){
        Gadget gadget = mobileFactory.create(reader,writer);
        assertEquals(gadget.getModel(),LINE_STUB);
        assertEquals(gadget.getFirm(),LINE_STUB);
        assertEquals(gadget.getSerialNumber(),LINE_STUB);
        assertEquals(gadget.getPrice(),BIG_DECIMAL_STUB);
    }

    @Test
    public void testOnCreatingProperInstance(){
        Gadget gadget = mobileFactory.create(reader,writer);
        assertTrue(gadget instanceof Gadget);
        assertTrue(gadget instanceof MobileGadget);
        assertFalse(gadget instanceof Smartphone);
    }
}