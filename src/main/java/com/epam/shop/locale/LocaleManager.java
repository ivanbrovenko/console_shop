package com.epam.shop.locale;

import com.epam.shop.reader.Reader;
import com.epam.shop.storage.ApplicationContext;
import com.epam.shop.writer.Writer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocaleManager {
    private Map<String,Locale> locales = new HashMap<>();
    private Reader reader;
    private Writer writer;

    public LocaleManager(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
        locales.put("1",new Locale("ru"));
        locales.put("2",new Locale("en"));
    }

    public void setLanguage(){
        writer.writeLine("Choose application language:\n1.Русский\n2.English");
        String language = reader.readLine();
        Locale.setDefault(locales.get(language));
    }
}
