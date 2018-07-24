package com.epam.shop.annotations;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TranslationService {
    String key();
}
