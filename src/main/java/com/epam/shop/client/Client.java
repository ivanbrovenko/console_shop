package com.epam.shop.client;

import com.epam.shop.writer.Writer;
import com.epam.shop.writer.impl.ConsoleWriter;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private final static int portTCP = 3000;
    private Writer writer = new ConsoleWriter(System.out);
    private final static String TYPE_A_COMMAND = "Type a command";
    private final static String WRONG_INPUT = "Wrong input";

    public void runClient(){
        while (true) {
            try(Socket socket = new Socket("localhost", portTCP);) {
                writer.writeLine(TYPE_A_COMMAND);
                BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                socketWriter.write(consoleReader.readLine());
                socketWriter.newLine();
                socketWriter.flush();
                writer.writeLine(socketReader.readLine());
            } catch (SocketException | UnknownHostException e) {
                writer.writeLine(e.getMessage());
                break;
            } catch (IOException e) {
                writer.writeLine(WRONG_INPUT);
            }
        }
    }

    public static void main(String[] args) {
       new Client().runClient();
    }
}
