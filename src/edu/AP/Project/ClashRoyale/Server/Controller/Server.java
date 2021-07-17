package edu.AP.Project.ClashRoyale.Server.Controller;

import edu.AP.Project.ClashRoyale.Server.Network.ConnectionListener;
import edu.AP.Project.ClashRoyale.Server.View.ServerCLUI;
import edu.AP.Project.ClashRoyale.Server.View.ServerUI;

public class Server implements Runnable{

    private final Object stateLock;
    private String  state;
    private ConnectionListener listener;
    private Thread listenerThread;

    public Server() {
        stateLock = new Object();
        state = "Running";
    }

    @Override
    public void run() {
        ServerUI ui = new ServerCLUI(this);
        ui.start();
        listener = new ConnectionListener(ui, this);
        listenerThread = new Thread(listener);
        listenerThread.start();
        while (!Thread.interrupted()) {
            synchronized (stateLock) {
                if (state.equals("Interrupted")) {
                    Thread.currentThread().interrupt();
                    continue;
                }
            }
        }
    }

    public void stop() {
        System.out.println("Trying to stop the server");
        listenerThread.interrupt();
        synchronized (stateLock) {
            state = "Interrupted";
        }
    }
}
