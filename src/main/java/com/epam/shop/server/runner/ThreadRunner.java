package com.epam.shop.server.runner;

import com.epam.shop.server.dispatcher.Dispatcher;
import com.epam.shop.server.factory.AbstractConfigFactory;
import com.epam.shop.server.handler.container.HandlerContainer;
import com.epam.shop.server.request.Request;
import com.epam.shop.server.response.Response;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadRunner extends Thread {
    private ServerSocket serverSocket;
    private AbstractConfigFactory abstractConfigFactory;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private HandlerContainer handlerContainer;

    public ThreadRunner(ServerSocket serverSocket, AbstractConfigFactory abstractConfigFactory, HandlerContainer handlerContainer) {
        this.serverSocket = serverSocket;
        this.abstractConfigFactory = abstractConfigFactory;
        this.handlerContainer = handlerContainer;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                Response response = abstractConfigFactory.getResponseInstance();
                Request request = abstractConfigFactory.getRequestInstance();
                Dispatcher requestDispatcher = new Dispatcher(socket, handlerContainer, request, response);
                executorService.submit(requestDispatcher::processRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
