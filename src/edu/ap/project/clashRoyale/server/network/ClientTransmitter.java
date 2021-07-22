package edu.ap.project.clashRoyale.server.network;

import edu.ap.project.clashRoyale.model.instructions.client.ClientInstruction;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTransmitter implements Runnable{
    private Socket socket;
    private ClientInstruction instruction;

    /**
     * Client transmitter
     * @param socket socket connection
     * @param instruction Client instruction to transmit
     */
    public ClientTransmitter(Socket socket, ClientInstruction instruction) {
        this.socket = socket;
        this.instruction = instruction;
    }

    /**
     * set client instruction to instruction
     * @param instruction client instruction
     */
    public void setInstruction(ClientInstruction instruction) {
        this.instruction = instruction;
    }

    /**
     * override runnable Interface
     */
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
//            try {
//                if (oos != null)
//                    oos.close();
//                if (os != null)
//                    os.close();
//            } catch (IOException e) {
//                System.out.println("Error while trying to close ClientTransmitter connection : " + e.toString());
            }
        while (!Thread.interrupted()){

        }
    }
}
