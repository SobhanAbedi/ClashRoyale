package edu.AP.Project.ClashRoyale.Server.Network;

import edu.AP.Project.ClashRoyale.Model.GlobalVariables;
import edu.AP.Project.ClashRoyale.Server.Controller.Server;
import edu.AP.Project.ClashRoyale.Server.View.ServerUI;
import edu.AP.Project.ClashRoyale.Model.ConsoleColors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ConnectionListener implements Runnable {
    private ServerUI ui;
    private Server server;

    public ConnectionListener(ServerUI ui, Server server) {
        this.ui = ui;
        this.server = server;
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(GlobalVariables.PORT);
        } catch (IOException e) {
            ui.sendError("Couldn't start a ServerSocket with the given port: " + e.toString());
            return;
        }
//        try {
//            serverSocket.setSoTimeout(GlobalVariables.LISTENER_TIMEOUT_DURATION);
//        } catch (SocketException e) {
//            ui.sendError("Couldn't set timout for Listener " + e.toString());
//        }
        ui.sendVerification("Listener Started " + serverSocket.getLocalSocketAddress());
        while (!Thread.interrupted()) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                ui.sendMessage("New Client Connected " + socket.getRemoteSocketAddress(), "\n", ConsoleColors.GREEN);
                server.newClient(socket);
            } catch (SocketTimeoutException e) {

            } catch (IOException e) {
                ui.sendError("Some IO Exception happened while waiting on new clients to join: " + e.toString());
                Thread.currentThread().interrupt();
            }
        }
        ui.sendMessage("Closing The Listener", "\n", "DEFAULT");
    }
}
