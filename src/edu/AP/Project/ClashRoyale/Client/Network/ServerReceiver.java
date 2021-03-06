package edu.AP.Project.ClashRoyale.Client.Network;

import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerReceiver implements Runnable{
    private Socket socket;
    //You also need to add some kind of controller connection

    public ServerReceiver(Socket socket) {
        this.socket = socket;
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
            ClientInstruction instruction;
            try {
                instruction = (ClientInstruction) ois.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println("Class of Serialized Object cannot be found: " + e.toString());
                continue;
            } catch (InterruptedIOException e){
                Thread.currentThread().interrupt();
                continue;
            } catch (IOException e) {
                System.out.println("Error while trying to receive a message from server: " + e.toString());
                continue;
            }
            if (instruction == null)
                continue;

            //TODO: Do something with the received message
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
