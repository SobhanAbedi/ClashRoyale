package edu.AP.Project.ClashRoyale.Server.Model;

import edu.AP.Project.ClashRoyale.Model.Card;
import edu.AP.Project.ClashRoyale.Model.Forces.Force;
import edu.AP.Project.ClashRoyale.Model.GlobalVariables;
import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstructionKind;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;
import edu.AP.Project.ClashRoyale.Model.PlayerInfo;
import edu.AP.Project.ClashRoyale.Server.Controller.Server;
import edu.AP.Project.ClashRoyale.Server.Network.ClientReceiver;
import edu.AP.Project.ClashRoyale.Server.Network.ClientTransmitter;

import java.net.Socket;
import java.util.HashMap;

public class ClientHandler{
    private Socket socket;
    private Server server;
    private DBConnector dbConnector;
    private ClientReceiver clientReceiver;
    private Thread receiverThread;
    private PlayerInfo clientInfo;
    private HashMap<String, Force> forces;
    private String[] deck;
    private Card[] cards;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        dbConnector = new DBConnector();
        dbConnector.connect();
//        this.clientReceiver = new ClientReceiver(this.socket, this);
        receiverThread = new Thread(clientReceiver);
        receiverThread.start();
        clientInfo = null;
        forces = null;
        deck = null;
        cards = null;
    }

    public void close() {
        receiverThread.interrupt();
        dbConnector.disconnect();
        server.closeClient(this);
    }

    private void loadUser(boolean reloadClientInfo) {
        if(reloadClientInfo)
            clientInfo = dbConnector.getUserInfo(clientInfo.getUserID());
        loadDeck();
        loadAllForces();
        loadAllCards();
    }

    private void loadDeck() {
        deck = dbConnector.getDeck(clientInfo.getUserID());
    }

    private void loadAllForces() {
        if(clientInfo == null) {
            sendUserInfoInvalid();
            return;
        }
        HashMap<String, Force> forces = server.getAllForcesOfLevel(clientInfo.getLevel());
        if(forces == null) {
            sendUserInfoInvalid();
            return;
        }
    }

    private void loadAllCards() {
        if(clientInfo == null) {
            sendUserInfoInvalid();
            return;
        }

        Card[] cards = server.getCards(true);
        String[] deck = dbConnector.getDeck(clientInfo.getUserID());
        if(deck == null) {
            sendUserInfoInvalid();
            return;
        }

        HashMap<String, Integer> deckMap = new HashMap<>();
        for(int i = 0; i < GlobalVariables.DECK_SIZE; i++) {
            if(deck[i] != null)
                deckMap.put(deck[i], i);
        }
        for(Card card : cards) {
            Integer loc = deckMap.get(card.getName());
            if(loc != null)
                card.setDeckLocation(loc);
        }
    }

    //TODO: move encryption to client side.(you can send byte[] as Objects)
    public ClientInstruction loginCheck(ServerInstruction instruction) {
        String username = (String) instruction.getArg(0);
        String password = (String) instruction.getArg(1);

        ClientTransmitter transmitter = new ClientTransmitter(socket, null);
        int res = dbConnector.login(username, password);
        switch (res) {
            case -1:
                //send connection problem message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Connection Problem"));
                return new ClientInstruction(ClientInstructionKind.FAIL, "Connection Problem");
//                break;
            case -2:
            case -3:
                //send wrong password or username message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Wrong Username or Password"));
                return new ClientInstruction(ClientInstructionKind.FAIL, "Wrong Username or Password");
//                break;
            case -5:
            case -6:
                //send something went wrong message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Something Went Wrong"));
                return new ClientInstruction(ClientInstructionKind.FAIL, "Something Went Wrong");
//                break;
            default:
                //send login successful message with userid
                clientInfo = dbConnector.getUserInfo(res);
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.SUCCESS, res));
                return new ClientInstruction(ClientInstructionKind.SUCCESS , res);
//                break;
        }
//        new Thread(transmitter).start();

    }

    public ClientInstruction signupCheck(ServerInstruction instruction) {
        String username = (String) instruction.getArg(0);
        String password = (String) instruction.getArg(1);

        ClientTransmitter transmitter = new ClientTransmitter(socket, null);
        int res = dbConnector.signup(username, password);
        switch (res) {
            case -1:
                //send connection problem message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Connection Problem"));
                return new ClientInstruction(ClientInstructionKind.FAIL, "Connection Problem");
//                break;
            case -2:
                //send username already taken message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Username Already Taken"));
                return new ClientInstruction(ClientInstructionKind.FAIL, "Username Already Taken");
//                break;
            case -5:
            case -6:
                //send something went wrong message
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Something Went Wrong"));
                return new ClientInstruction(ClientInstructionKind.FAIL, "Something Went Wrong");
//                break;
            default:
                //send signup successful message with userid
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.SUCCESS, res));
                return new ClientInstruction(ClientInstructionKind.SUCCESS, res);
//                break;
        }
//        new Thread(transmitter).start();
    }

    public void getPlayerInfo(ServerInstruction instruction) {
        int id = (Integer) instruction.getArg(0);
        boolean forceCheck = (Boolean) instruction.getArg(1);

        ClientTransmitter transmitter = new ClientTransmitter(socket, null);
        if(clientInfo == null || forceCheck) {
            PlayerInfo res = dbConnector.getUserInfo(id);
            if (res == null) {
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.FAIL, "Wrong userid or Something went wrong"));
            } else {
                transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.USER_INFO, res));
            }
        } else {
            transmitter.setInstruction(new ClientInstruction(ClientInstructionKind.USER_INFO, clientInfo));
        }
        new Thread(transmitter).start();
    }

    public void getAllCards(ServerInstruction instruction) {
        //No arguments
        if(cards == null)
            loadAllCards();
        new Thread(new ClientTransmitter(socket, new ClientInstruction(ClientInstructionKind.ALL_CARDS, (Object) cards)));
    }

    public void getForceInfo(ServerInstruction instruction) {
        String forceName = (String) instruction.getArg(0);

        if(clientInfo == null) {
            sendUserInfoInvalid();
            return;
        }

        Force force = server.getForceInfo(forceName, clientInfo.getLevel());
        if(force == null) {
            sendUserInfoInvalid();
            return;
        }
        new Thread(new ClientTransmitter(socket, new ClientInstruction(ClientInstructionKind.FORCE_INFO, force)));
    }

    public void getAllForces(ServerInstruction instruction) {
        if(forces == null)
            loadAllForces();
        new Thread(new ClientTransmitter(socket, new ClientInstruction(ClientInstructionKind.ALL_FORCES_INFO, forces)));
    }

    public void updateScore(int newScore) {
        int userid = clientInfo.getUserID();
        if(dbConnector.updateUserScore(userid, newScore) == 0) {
            clientInfo = dbConnector.getUserInfo(userid);
        }
    }

    public void updateDeck(ServerInstruction instruction) {
        int place = (Integer) instruction.getArg(0);
        String cardName = (String) instruction.getArg(1);
        if(clientInfo == null) {
            sendUserInfoInvalid();
            return;
        }

        if(dbConnector.updateDeck(clientInfo.getUserID(), place, cardName) == 0) {
            new Thread(new ClientTransmitter(socket, new ClientInstruction(ClientInstructionKind.SUCCESS, "Deck Changed")));
        } else {
            new Thread(new ClientTransmitter(socket, new ClientInstruction(ClientInstructionKind.FAIL, "Wrong Deck Location")));
        }
    }

    public void startTrainingCamp(boolean smart, ServerInstruction instruction) {
        //TODO: Finish this function
    }

    public void joinPool(boolean withAlly, ServerInstruction instruction) {
        //TODO: Finish this function
    }

    public String getUsername() {
        if(clientInfo == null)
            return null;
        return clientInfo.getUsername();
    }

    public Card[] getDeckCards() {
        if(cards == null)
            loadAllCards();
        Card[] deckCards = new Card[GlobalVariables.DECK_SIZE];
        for(Card card : cards) {
            int deckLocation = card.getDeckLocation();
            if(deckLocation > 0)
                deckCards[deckLocation - 1] = card;
        }
        return deckCards;
    }

    private void sendUserInfoInvalid() {
        new Thread(new ClientTransmitter(socket, new ClientInstruction(ClientInstructionKind.FAIL, "UserInfo not available. Please Login again.")));
    }
}
