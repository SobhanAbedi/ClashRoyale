package edu.AP.Project.ClashRoyale.Server.Network;

import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstruction;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTransmitter implements Runnable{
    private Socket socket;
    private ClientInstruction instruction;

    public ClientTransmitter(Socket socket, ClientInstruction instruction) {
        this.socket = socket;
        this.instruction = instruction;
    }

    public void setInstruction(ClientInstruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public void run() {
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(instruction);
        } catch (IOException e) {
            System.out.println("Error while trying to send a message to client: " + e.toString());
        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (os != null)
                    os.close();
            } catch (IOException e) {
                System.out.println("Error while trying to close ClientTransmitter connection : " + e.toString());
            }
        }
    }
}
