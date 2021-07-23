package edu.ap.project.clashRoyale.server.view;

import edu.ap.project.clashRoyale.model.ConsoleColors;
import edu.ap.project.clashRoyale.server.controller.Server;

import java.util.Scanner;

public class ServerCLUI extends ServerUI {

    MessageDisplay display;
    MessageListener listener;
    Thread listenerThread;
    Server server;

    /**
     * ServerCommandLine UI Constructor
     * @param server server
     */
    public ServerCLUI(Server server) {
        display = new MessageDisplay();
        listener = new MessageListener();
        listenerThread = new Thread(listener);
        this.server = server;
    }

    /**
     * start server
     */
    @Override
    public void start() {
        listenerThread.start();
    }

    /**
     * Stop server
     */
    @Override
    public void stop() {
        listenerThread.interrupt();
    }

    /**
     * send message
     * @param message message
     * @param ending ending of message
     * @param color color of message
     */
    @Override
    public void sendMessage(String message, String ending, String color) {
        if(color == null || color.equals("DEFAULT")) {
            color = ConsoleColors.PURPLE;
        }
        display.print(color + message + ending + ConsoleColors.RESET);
    }

    /**
     * Send verification
     * @param message verification message
     */
    @Override
    public void sendVerification(String message) {
        sendMessage(message, "\n", ConsoleColors.GREEN);
    }

    /**
     * send error
     * @param message error message
     */
    @Override
    public void sendError(String message) {
        sendMessage(message, "\n", ConsoleColors.RED);
    }

    /**
     * stop server
     */
    @Override
    public void stopServer() {
        server.stop();
    }

    /**
     * Class for display Message
     */
    private class MessageDisplay {
        /**
         * print message
         * @param str string to print
         */
        public void print(String str) {
            System.out.print(str);
        }
    }

    /**
     * Class to read message from
     */
    private class MessageListener implements Runnable{
        Scanner scanner;

        /**
         * Message listener
         */
        public MessageListener() {
            scanner = new Scanner(System.in);
        }

        /**
         * Override run from runnable thread
         */
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




