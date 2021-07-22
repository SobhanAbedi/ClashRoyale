package edu.ap.project.clashRoyale.server.view;

import edu.ap.project.clashRoyale.model.ConsoleColors;
import edu.ap.project.clashRoyale.server.controller.Server;

import java.util.Scanner;

public class ServerCLUI extends ServerUI {

    MessageDisplay display;
    MessageListener listener;
    Thread listenerThread;
    Server server;
    public ServerCLUI(Server server) {
        display = new MessageDisplay();
        listener = new MessageListener();
        listenerThread = new Thread(listener);
        this.server = server;
    }

    @Override
    public void start() {
        listenerThread.start();
    }

    @Override
    public void stop() {
        listenerThread.interrupt();
    }

    @Override
    public void sendMessage(String message, String ending, String color) {
        if(color == null || color.equals("DEFAULT")) {
            color = ConsoleColors.PURPLE;
        }
        display.print(color + message + ending + ConsoleColors.RESET);
    }

    @Override
    public void sendVerification(String message) {
        sendMessage(message, "\n", ConsoleColors.GREEN);
    }

    @Override
    public void sendError(String message) {
        sendMessage(message, "\n", ConsoleColors.RED);
    }

    @Override
    public void stopServer() {
        server.stop();
    }

    private class MessageDisplay {
        public void print(String str) {
            System.out.print(str);
        }
    }

    private class MessageListener implements Runnable{
        Scanner scanner;

        public MessageListener() {
            scanner = new Scanner(System.in);
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                String input = scanner.nextLine();
                switch (input) {
                    case "CLOSE_SERVER" :
                        stopServer();
                        Thread.currentThread().interrupt();
                        break;
                }
            }
        }
    }

}




