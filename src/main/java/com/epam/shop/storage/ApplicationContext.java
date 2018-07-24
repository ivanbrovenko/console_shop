package com.epam.shop.storage;

import com.epam.shop.entity.Gadget;
import com.epam.shop.dao.BasketDAO;
import com.epam.shop.dao.OrderDAO;
import com.epam.shop.dao.ProductDAO;
import com.epam.shop.dao.impl.BasketDAOImpl;
import com.epam.shop.dao.impl.OrderDAOImpl;
import com.epam.shop.dao.impl.ProductDAOImpl;
import com.epam.shop.cache.LRUCache;
import com.epam.shop.exception.ApplicationTechnicalException;
import com.epam.shop.exception.DuplicateSerialException;
import com.epam.shop.filler.FillerContext;
import com.epam.shop.locale.LocaleManager;
import com.epam.shop.locale.MessagesContainer;
import com.epam.shop.reader.ConsoleReader;
import com.epam.shop.reader.Reader;
import com.epam.shop.server.factory.AbstractConfigFactory;
import com.epam.shop.server.factory.impl.HTTPAbstractFactoryImpl;
import com.epam.shop.server.factory.impl.TCPAbstractFactoryImpl;
import com.epam.shop.server.handler.container.HandlerContainer;
import com.epam.shop.server.Server;
import com.epam.shop.service.BasketService;
import com.epam.shop.service.OrderService;
import com.epam.shop.service.PersistenceService;
import com.epam.shop.service.impl.BasketServiceImpl;
import com.epam.shop.service.impl.OrderServiceImpl;
import com.epam.shop.service.ProductService;
import com.epam.shop.service.impl.PersistenceServiceImpl;
import com.epam.shop.service.impl.ProductServiceImpl;
import com.epam.shop.writer.impl.ConsoleWriter;
import com.epam.shop.writer.Writer;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class for initializing all the necessary containers
 */
public class ApplicationContext {
    /**
     * File
     */
    private File file;
    /**
     * LRU cache object
     */
    private LRUCache cache;
    /**
     * Product service
     */
    private ProductService productService;
    /**
     * Order service
     */
    private OrderService orderService;
    /**
     * Writer
     */
    private Writer writer;
    /**
     * Basket service
     */
    private BasketService basketService;
    /**
     * Reader
     */
    private Reader reader;
    /**
     * Persistence service
     */
    private PersistenceService persistenceService;
    /**
     * FillerStrategyFacade executor to fill {@link Gadget} in a different ways
     */
    private MessagesContainer messagesContainer;
    private FillerContext fillerContext;
    private HandlerContainer handlerContainer;

    /**
     * Initial constructor
     */
    public ApplicationContext() throws DuplicateSerialException, ApplicationTechnicalException {
        this.file = new File("src/main/resources/file.txt");
        ProductDAO productDAO = new ProductDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        BasketDAO basketDAO = new BasketDAOImpl();
        this.cache = new LRUCache(5);
        this.productService = new ProductServiceImpl(productDAO, cache);
        this.orderService = new OrderServiceImpl(orderDAO, productDAO, basketDAO, cache);
        this.writer = new ConsoleWriter(System.out);
        this.persistenceService = new PersistenceServiceImpl();
        this.basketService = new BasketServiceImpl(basketDAO, productDAO);
        List<Gadget> gadgets = persistenceService.readFromProductDAO(file);
        for (Gadget gadget : gadgets) {
            productService.add(gadget);
        }
        this.reader = new ConsoleReader(System.in);
        new LocaleManager(reader, writer).setLanguage();
        this.messagesContainer = new MessagesContainer();
        this.handlerContainer = new HandlerContainer(productService);
        try {
            new Server(3000, new TCPAbstractFactoryImpl(), handlerContainer).start();
            new Server(3001, new HTTPAbstractFactoryImpl(), handlerContainer).start();
        } catch (IOException e) {
            writer.writeLine(e.getMessage());
        }
    }

    public LRUCache getCache() {
        return cache;
    }

    public void setCache(LRUCache cache) {
        this.cache = cache;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public BasketService getBasketService() {
        return basketService;
    }

    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getStringFromMessageContainer(String messageKey) {
        return messagesContainer.getString(messageKey);
    }

    public FillerContext getFillerContext() {
        return fillerContext;
    }

    public void setFillerContext(FillerContext fillerContext) {
        this.fillerContext = fillerContext;
    }
}