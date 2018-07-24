package com.epam.shop.server;

import com.epam.shop.cache.LRUCache;
import com.epam.shop.dao.ProductDAO;
import com.epam.shop.dao.impl.ProductDAOImpl;
import com.epam.shop.entity.Gadget;
import com.epam.shop.exception.DuplicateSerialException;
import com.epam.shop.server.factory.AbstractConfigFactory;
import com.epam.shop.server.factory.impl.TCPAbstractFactoryImpl;
import com.epam.shop.server.handler.container.HandlerContainer;
import com.epam.shop.service.ProductService;
import com.epam.shop.service.impl.ProductServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;

import static com.epam.shop.messages.Messages.EXCEPTION_NO_SUCH_COMMAND;
import static com.epam.shop.messages.Messages.EXCEPTION_NO_SUCH_GADGET;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServerTest {
    private static Server server;
    private int port = 3000;
    private static HandlerContainer handlerContainer;
    private static ProductService productService;
    private static ProductDAO productDAO;
    private static LRUCache lruCache;
    private final static Gadget STUB = new Gadget("stub","stub",new BigDecimal(666),"stub");
    private final static Gadget STUB_SERIAL_5 = new Gadget("5","5",new BigDecimal(5),"5");
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private final static String PRODUCT_COUNT_1 = "count:1";
    private final static String GET_COUNT = "get_count";
    private final static String GET_ITEM = "get_item=";
    private final static String RESPONSE_TO_GET_ITEM_5 = "name:5| price:5";
    @Mock
    private BufferedReader scanner;

    @BeforeClass
    public static void startServer() throws IOException {
        productDAO = new ProductDAOImpl();
        lruCache = new LRUCache(5);
        productService = new ProductServiceImpl(productDAO, lruCache);
        handlerContainer = new HandlerContainer(productService);
        server = new Server(3000, new TCPAbstractFactoryImpl(), handlerContainer);
        server.start();
    }

    @Before
    public void setUp() throws Exception {
        socket = new Socket("localhost", 3000);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Test
    public void testTCPServerOnNoSuchGadgetMessage() throws IOException, DuplicateSerialException {
        when(scanner.readLine()).thenReturn(GET_ITEM + "666");
        readInput();
        assertEquals(br.readLine(), EXCEPTION_NO_SUCH_GADGET);
    }

    @Test
    public void testTCPServerOnNoSuchCommandMessage() throws IOException {
        when(scanner.readLine()).thenReturn(EXCEPTION_NO_SUCH_COMMAND);
        readInput();
        assertEquals(br.readLine(), EXCEPTION_NO_SUCH_COMMAND);
    }

    @Test
    public void testTCPServerOnReturnProductCount1() throws IOException, DuplicateSerialException {
        productService.add(STUB);
        when(scanner.readLine()).thenReturn(GET_COUNT);
        readInput();
        assertEquals(br.readLine(), PRODUCT_COUNT_1);
    }

    @Test
    public void testTCPServerOnReturnProductWithDefinedSerial() throws DuplicateSerialException, IOException {
        productService.add(STUB_SERIAL_5);
        when(scanner.readLine()).thenReturn(GET_ITEM + "5");
        readInput();
        assertEquals(br.readLine(), RESPONSE_TO_GET_ITEM_5);
    }

    private void readInput() throws IOException {
        String str = scanner.readLine();
        bw.write(str);
        bw.newLine();
        bw.flush();
    }
}