package edu.ap.project.clashRoyale.server.controller;


public class Main {
    public static void main(String[] args) {
        Thread IranServer = new Thread(new Server());
        IranServer.start();

    }
}
