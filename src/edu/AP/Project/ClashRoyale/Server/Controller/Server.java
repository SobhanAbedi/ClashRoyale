package edu.AP.Project.ClashRoyale.Server.Controller;

import edu.AP.Project.ClashRoyale.Model.Card;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstructionKind;
import edu.AP.Project.ClashRoyale.Server.Model.ClientHandler;
import edu.AP.Project.ClashRoyale.Server.Model.DBConnector;
import edu.AP.Project.ClashRoyale.Server.Network.ConnectionListener;
import edu.AP.Project.ClashRoyale.Server.View.ServerCLUI;
import edu.AP.Project.ClashRoyale.Server.View.ServerUI;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Server implements Runnable{

    private final Object stateLock;
    private String  state;
    private ConnectionListener listener;
    private Thread listenerThread;
    private ArrayList<ClientHandler> handlerList;
    private Card[] cards;
    private DBConnector dbConnector;

    public Server() {
        stateLock = new Object();
        state = "Running";
        cards = null;
        dbConnector = new DBConnector();
    }

    @Override
    public void run() {
        ServerUI ui = new ServerCLUI(this);
        ui.start();
        listener = new ConnectionListener(ui, this);
        listenerThread = new Thread(listener);
        listenerThread.start();
        byte[] bArray = new byte[2];
        bArray[0] = 12;
        bArray[1] = 25;
        ServerInstruction instruction = new ServerInstruction(ServerInstructionKind.LOGIN, "SobhanAbedi", "TestPass", true, (Float)12.5f, bArray);
        System.out.println(instruction.toString());
        if(dbConnector.connect() == 0)
            ui.sendVerification("DataBase Connected");
        ui.sendMessage(Integer.toString(dbConnector.signup("SobhanAbedi", "TestPass")), "\n", null);
        try {
            System.out.println(dbConnector.usernameExists("SobhanAbedi"));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        ui.sendMessage(Integer.toString(dbConnector.login("SobhanAbedi", "TestPass")), "\n", null);

        Card[] cards = dbConnector.getAllCardsGeneral();
        System.out.println("cards length: " + cards.length);
        for(Card card : cards) {
            System.out.println("Card: " + card.getName() + ", " + card.getCost() + ", " + Arrays.toString(card.getForces()));
        }
        System.out.println("Deck: " + Arrays.toString(dbConnector.getDeck(6)));



        if(dbConnector.disconnect() == 0)
            ui.sendVerification("DataBase Disconnected");
        else
            ui.sendError("DataBase Connection Problem");
        while (!Thread.interrupted()) {
            synchronized (stateLock) {
                if (state.equals("Interrupted")) {
                    Thread.currentThread().interrupt();
                    continue;
                }
            }
        }
    }

    public void newClient(Socket socket) {
        ClientHandler handler = new ClientHandler(socket, this);
        handlerList.add(handler);
    }

    public void closeClient(ClientHandler handler) {
        handlerList.remove(handler);
    }

    public void stop() {
        System.out.println("Trying to stop the server");
        listenerThread.interrupt();
        synchronized (stateLock) {
            state = "Interrupted";
        }
    }

    public Card[] getCards(boolean copy) {
        if(cards == null)
            cards = dbConnector.getAllCardsGeneral();
        if(copy) {
            Card[] newCards = new Card[cards.length];
            for(int i = 0; i < cards.length; i++) {
                try {
                    newCards[i] = (Card)cards[i].clone();
                } catch (CloneNotSupportedException e) {
                    System.out.println(e.toString());
                }
            }
            return newCards;
        }
        return cards;
    }


}
