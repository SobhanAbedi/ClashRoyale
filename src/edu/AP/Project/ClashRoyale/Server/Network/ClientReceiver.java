package edu.AP.Project.ClashRoyale.Server.Network;

import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;
import edu.AP.Project.ClashRoyale.Server.Controller.Server;
import edu.AP.Project.ClashRoyale.Server.Model.ClientHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReceiver implements Runnable {
    private Socket socket;
    private ClientHandler handler;

    public ClientReceiver(Socket socket, ClientHandler handler) {
        this.socket = socket;
        this.handler = handler;
    }

    @Override
    public void run() {
        InputStream is = null;
        ObjectInputStream ois = null;
        try {
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
        } catch (IOException e) {
            System.out.println("Error while trying to establish ObjectInputStream in ClientReceiver: " + e.toString());
            Thread.currentThread().interrupt();
        }

        while (!Thread.interrupted()) {
            ServerInstruction instruction;
            try {
                instruction = (ServerInstruction) ois.readObject();
            } catch (ClassNotFoundException e){
                System.out.println("Class of Serialized Object cannot be found: " + e.toString());
                continue;
            } catch (IOException e) {
                System.out.println("Error while trying to receive a message from client: " + e.toString());
                continue;
            }
            if(instruction == null)
                continue;
            switch (instruction.getKind()) {
                case LOGIN:
                    handler.loginCheck(instruction);
                    break;
                case SIGNUP:
                    handler.signupCheck(instruction);
                    break;
            }
        }

        try {
            if(ois != null)
                ois.close();
            if(is != null)
                is.close();
        } catch (IOException e) {
            System.out.println("Error while trying to close ClientReceiver connection : " + e.toString());
        }
    }
}
