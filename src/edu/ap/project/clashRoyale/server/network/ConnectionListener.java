package edu.ap.project.clashRoyale.server.network;

import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.server.controller.Server;
import edu.ap.project.clashRoyale.server.view.ServerUI;
import edu.ap.project.clashRoyale.model.ConsoleColors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectionListener implements Runnable {
    private ServerUI ui;
    private Server server;

    /**
     * connection listener constructor
     * @param ui Serrver UI
     * @param server server
     */
    public ConnectionListener(ServerUI ui, Server server) {
        this.ui = ui;
        this.server = server;
    }

    /**
     * implement run method of runnable interface
     */
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
