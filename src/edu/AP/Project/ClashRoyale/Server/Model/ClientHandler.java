package edu.AP.Project.ClashRoyale.Server.Model;

import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstructionKind;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;
import edu.AP.Project.ClashRoyale.Server.Controller.Server;
import edu.AP.Project.ClashRoyale.Server.Network.ClientReceiver;
import edu.AP.Project.ClashRoyale.Server.Network.ClientTransmitter;

import java.net.Socket;
import java.util.HashMap;

public class ClientHandler{
    Socket socket;
    Server server;
    DBConnector dbConnector;
    ClientReceiver clientReceiver;
    Thread receiverThread;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        dbConnector = new DBConnector();
        dbConnector.connect();
        this.clientReceiver = new ClientReceiver(this.socket, this);
        receiverThread = new Thread(clientReceiver);
        receiverThread.start();
    }

    public void close() {
        receiverThread.interrupt();
        dbConnector.disconnect();
        server.closeClient(this);
    }

    //TODO: move encryption to client side.(you can send byte[] as Objects)
    public void loginCheck(ServerInstruction instruction) {
        String username = (String) instruction.getArg(0);
        String password = (String) instruction.getArg(1);
        ClientTransmitter transmitter = new ClientTransmitter(socket, null);
        int res = dbConnector.login(username, password);
        switch (res) {
            case -1:
                //send connection problem message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Connection Problem"));
                break;
            case -2:
            case -3:
                //send wrong password or username message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Wrong Username or Password"));
                break;
            case -5:
            case -6:
                //send something went wrong message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Something Went Wrong"));
                break;
            default:
                //send login successful message with userid
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.SUCCESS, res));
                break;
        }
        new Thread(transmitter).start();
    }

    public void signupCheck(ServerInstruction instruction) {
        String username = (String) instruction.getArg(0);
        String password = (String) instruction.getArg(1);
        ClientTransmitter transmitter = new ClientTransmitter(socket, null);
        int res = dbConnector.login(username, password);
        switch (res) {
            case -1:
                //send connection problem message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Connection Problem"));
                break;
            case -2:
                //send username already taken message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Username Already Taken"));
                break;
            case -5:
            case -6:
                //send something went wrong message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Something Went Wrong"));
                break;
            default:
                //send signup successful message with userid
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.SUCCESS, res));
                break;
        }
        new Thread(transmitter).start();
    }
}
