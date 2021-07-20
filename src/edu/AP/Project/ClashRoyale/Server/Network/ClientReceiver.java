package edu.AP.Project.ClashRoyale.Server.Network;

import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;
import edu.AP.Project.ClashRoyale.Server.Model.ClientHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
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
        int counter = 0;
        while (!Thread.interrupted()) {
            ServerInstruction instruction;
            try {

                System.out.println(socket.isClosed());
                instruction = (ServerInstruction) ois.readObject();
            } catch (ClassNotFoundException e){
                System.out.println("Class of Serialized Object cannot be found: " + e.toString());
                continue;
            } catch (InterruptedIOException e){
                Thread.currentThread().interrupt();
                continue;
            } catch (IOException e) {
                if (counter % 10000 == 0) {
                    System.out.println("Error while trying to receive a message from client: " + e.toString());
//                    System.out.println(e.getStackTrace());
                    e.printStackTrace();
                }

                counter++;
                continue;
            }

            try {
                if(ois != null)
                    ois.close();
                if(is != null)
                    is.close();
            } catch (IOException e) {
                System.out.println("Error while trying to close ClientReceiver connection : " + e.toString());
            }
            if(instruction == null)
                continue;

//            switch (instruction.getKind()) {
//                case LOGIN:
//                    handler.loginCheck(instruction);
//                    break;
//                case SIGNUP:
//                    handler.signupCheck(instruction);
//                    break;
//                case GET_PLAYER_INFO:
//                    handler.getPlayerInfo(instruction);
//                    break;
//                case GET_ALL_CARDS:
//                    handler.getAllCards(instruction);
//                    break;
//                case GET_FORCE_INFO:
//                    handler.getForceInfo(instruction);
//                    break;
//                case GET_ALL_FORCES:
//                    handler.getAllForces(instruction);
//                    break;
//                case UPDATE_DECK:
//                    handler.updateDeck(instruction);
//                    break;
//                case START_TRAINING_CAMP:
//                    handler.startTrainingCamp(false, instruction);
//                    break;
//                case START_TRAINING_CAMP_SMART:
//                    handler.startTrainingCamp(true, instruction);
//                    break;
//                case JOIN_1V1_POOL:
//                    handler.joinPool(false, instruction);
//                    break;
//                case JOIN_2V2_POOL:
//                    handler.joinPool(true, instruction);
//            }
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
