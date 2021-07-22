package edu.ap.project.clashRoyale.client.network;

import edu.ap.project.clashRoyale.model.instructions.server.ServerInstruction;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This class remain unused because of Socket Problem
 */
public class ServerTransmitter implements Runnable{
    private Socket socket;
    private ServerInstruction instruction;

    public ServerTransmitter(Socket socket, ServerInstruction instruction) {
        this.socket = socket;
        this.instruction = instruction;
    }

    public void setInstruction(ServerInstruction instruction) {
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
            System.out.println("Error while trying to send a message to Server: " + e.toString());
        } finally {
//            try {
//                if (oos != null)
//                    oos.close();
//                if (os != null)
//                    os.close();
//            } catch (IOException e) {
//                System.out.println("Error while trying to close ServerTransmitter connection : " + e.toString());
//            }
        }
        System.out.println(socket.isClosed());
//        int counter =0;
//        while(true){
//            if (counter % 100000 == 0){
//                System.out.println("server Transmitter is ON");
//            }
//            counter++;
//        }
    }
}
