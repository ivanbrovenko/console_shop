package com.epam.shop.server;

import com.epam.shop.server.factory.AbstractConfigFactory;
import com.epam.shop.server.handler.container.HandlerContainer;
import com.epam.shop.server.runner.ThreadRunner;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private ServerSocket socket;
    private ThreadRunner threadRunner;
    private HandlerContainer handlerContainer;

    public Server(int port, AbstractConfigFactory abstractConfigFactory, HandlerContainer handlerContainer) throws IOException {
        this.socket = new ServerSocket(port);
        this.threadRunner = new ThreadRunner(socket, abstractConfigFactory, handlerContainer);
    }

    public void start() {
        threadRunner.start();
    }

    public void stop() throws IOException {
        threadRunner.interrupt();
        socket.close();
    }

}
