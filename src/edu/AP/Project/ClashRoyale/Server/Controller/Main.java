package edu.AP.Project.ClashRoyale.Server.Controller;


import edu.AP.Project.ClashRoyale.Server.View.ServerUI;

public class Main {
    public static void main(String[] args) {
        Thread IranServer = new Thread(new Server());
        IranServer.start();

    }
}
