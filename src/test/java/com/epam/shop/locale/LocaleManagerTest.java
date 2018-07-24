package com.epam.shop.locale;

import com.epam.shop.reader.Reader;
import com.epam.shop.writer.impl.ConsoleWriter;
import com.epam.shop.writer.Writer;
import com.epam.shop.writer.impl.EmptyWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Locale;

import static org.junit.Assert.*;

public class LocaleManagerTest {
    private LocaleManager localeManager;
    private static final String RUSSIAN = "ru";
    private static final String ENGLISH = "en";
    private static final String ONE_NUMBER = "1";
    private static final String TWO_NUMBER = "2";

    @Mock
    private Reader reader = Mockito.mock(Reader.class);
    private Writer writer;

    @Before
    public void setUp() throws Exception {
        writer = new EmptyWriter();
        localeManager = new LocaleManager(reader,writer);
    }

    @Test
    public void testSettingRussianLocale(){
        Mockito.when(reader.readLine()).thenReturn(ONE_NUMBER);
        localeManager.setLanguage();
        assertEquals(new Locale(RUSSIAN),Locale.getDefault());
    }

    @Test
    public void testSettingEnglishLocale(){
        Mockito.when(reader.readLine()).thenReturn(TWO_NUMBER);
        localeManager.setLanguage();
        assertEquals(new Locale(ENGLISH),Locale.getDefault());
    }
}