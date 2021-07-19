package edu.AP.Project.ClashRoyale.Client.Network;

import java.net.Socket;

public class ServerReceiver implements Runnable{
    private Socket socket;
    //You also need to add some kind of controller connection

    public ServerReceiver(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {

    }
}
