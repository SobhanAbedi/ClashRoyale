package edu.AP.Project.ClashRoyale.Client.Network;

import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;

import javax.sound.sampled.Port;
import java.net.Socket;

public class ServerTransmitter implements Runnable{
    private Socket socket;
    private ServerInstruction instruction;

    public ServerTransmitter(Socket socket, ServerInstruction instruction) {
        this.socket = socket;
        this.instruction = instruction;
    }

    @Override
    public void run() {

    }
}
