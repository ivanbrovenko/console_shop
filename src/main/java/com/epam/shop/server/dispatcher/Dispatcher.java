package com.epam.shop.server.dispatcher;

import com.epam.shop.exception.NoSuchCommandException;
import com.epam.shop.exception.NoSuchProductException;
import com.epam.shop.server.handler.Handler;
import com.epam.shop.server.handler.container.HandlerContainer;
import com.epam.shop.server.request.Request;
import com.epam.shop.server.response.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.epam.shop.messages.Messages.EXCEPTION_NO_SUCH_COMMAND;
import static com.epam.shop.messages.Messages.EXCEPTION_NO_SUCH_GADGET;

public class Dispatcher {
    private HandlerContainer handlerContainer;
    private Request request;
    private Response response;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Dispatcher(Socket socket, HandlerContainer handlerContainer, Request request, Response response) throws IOException {
        this.handlerContainer = handlerContainer;
        this.request = request;
        this.response = response;
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
    }

    public void processRequest() {
        try {
            String unparsedString = null;
            unparsedString = reader.readLine();
            Handler handler = handlerContainer.getHandlerContainer().get(request.getCommandName(unparsedString));
            checkForNullCommand(handler);
            handler.execute(response, writer, getParam(request,unparsedString));
        } catch (IOException | NoSuchCommandException | NoSuchProductException e) {
            writer.println(e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void checkForNullCommand(Handler handler) throws NoSuchCommandException {
        if (handler == null){
            throw new NoSuchCommandException(EXCEPTION_NO_SUCH_COMMAND);
        }
    }


    private String getParam(Request request,String unparsedString) throws NoSuchProductException {
        return request.getParams(unparsedString);
    }

}
