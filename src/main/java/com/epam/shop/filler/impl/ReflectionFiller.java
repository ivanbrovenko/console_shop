package com.epam.shop.filler.impl;

import com.epam.shop.annotations.TranslationService;
import com.epam.shop.entity.Gadget;
import com.epam.shop.exception.ApplicationTechnicalException;
import com.epam.shop.filler.Filler;
import com.epam.shop.locale.MessagesContainer;
import com.epam.shop.reader.RandomGeneratorReader;
import com.epam.shop.reader.Reader;
import com.epam.shop.writer.Writer;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReflectionFiller implements Filler {

    public void fill(Reader reader, Writer writer, Gadget gadget) throws ApplicationTechnicalException {
        try {
            for (Field field : findAllFields(gadget.getClass())) {
                writer.writeLine(new MessagesContainer().getString(field.getDeclaredAnnotation(TranslationService.class).key()));
                field.setAccessible(true);
                Map<Class<?>, String> methods = reader.getMethods();
                if (methods.containsKey(field.getType())) {
                    Method method = reader.getClass().getMethod(reader.getMethods().get(field.getType()));
                    field.set(gadget, method.invoke(reader));
                }
            }

        } catch (NoSuchMethodException |IllegalAccessException |InvocationTargetException e) {
            throw new ApplicationTechnicalException();
        }
    }

    private List<Field> findAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        if (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            fields.addAll(findAllFields(clazz.getSuperclass()));
        }
        return fields;
    }
}