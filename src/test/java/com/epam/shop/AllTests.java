package com.epam.shop;

import com.epam.shop.factory.GadgetFactoryTest;
import com.epam.shop.factory.MobileFactoryTest;
import com.epam.shop.factory.SmartphoneFactoryTest;
import com.epam.shop.filler.impl.ReflectionFillerTest;
import com.epam.shop.locale.LocaleManagerTest;
import com.epam.shop.server.ServerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({GadgetFactoryTest.class, MobileFactoryTest.class,
        SmartphoneFactoryTest.class, ReflectionFillerTest.class, LocaleManagerTest.class, ServerTest.class})
public class AllTests {
}