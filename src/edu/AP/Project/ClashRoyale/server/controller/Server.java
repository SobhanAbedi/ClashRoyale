package edu.ap.project.clashRoyale.server.controller;

import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.model.forces.Building;
import edu.ap.project.clashRoyale.model.forces.Force;
import edu.ap.project.clashRoyale.model.forces.Soldier;
import edu.ap.project.clashRoyale.model.forces.Spell;
import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.instructions.server.ServerInstruction;
import edu.ap.project.clashRoyale.model.instructions.server.ServerInstructionKind;
import edu.ap.project.clashRoyale.server.model.*;
import edu.ap.project.clashRoyale.server.model.gameEngine.GameEngine;
import edu.ap.project.clashRoyale.server.network.ConnectionListener;
import edu.ap.project.clashRoyale.server.view.ServerCLUI;
import edu.ap.project.clashRoyale.server.view.ServerUI;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Server implements Runnable{

    private final Object stateLock;
    private String  state;
    private ConnectionListener listener;
    private Thread listenerThread;
    private ArrayList<ClientHandler> handlerList;
    private DBConnector dbConnector;
    private Card[] cards;
    private HashMap<String, Force[]> forceList;
    private GamePool gamePool1v1;
    private Thread pool1v1Thread;
    private GamePool gamePool2v2;
    private Thread pool2v2Thread;
    private HashMap<GameEngine, Thread> engines;
    private HashMap<String, CardForces> cardForces;

    public Server() {
        stateLock = new Object();
        state = "Running";
        dbConnector = new DBConnector();
        cards = null;
        forceList = null;
        handlerList = new ArrayList<>();
        gamePool1v1 = new GamePool(2);
        gamePool2v2 = new GamePool(4);
        engines = new HashMap<>();
        cardForces = new HashMap<>();
    }

    @Override
    public void run() {
        ServerUI ui = new ServerCLUI(this);
        ui.start();
//        listener = new ConnectionListener(ui, this);
//        listenerThread = new Thread(listener);
//        listenerThread.start();
        byte[] bArray = new byte[2];
        bArray[0] = 12;
        bArray[1] = 25;
        ServerInstruction instruction = new ServerInstruction(ServerInstructionKind.LOGIN, "SobhanAbedi", "TestPass", true, (Float)12.5f, bArray);
        System.out.println(instruction.toString());
        if(dbConnector.connect() == 0)
            ui.sendVerification("DataBase Connected");
        ui.sendMessage(Integer.toString(dbConnector.signup("Hamidreza", "hamid")), "\n", null);
        try {
            System.out.println(dbConnector.usernameExists("SobhanAbedi"));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        ui.sendMessage(Integer.toString(dbConnector.login("SobhanAbedi", "TestPass")), "\n", null);

        cards = dbConnector.getAllCardsGeneral();
        System.out.println("cards length: " + cards.length);
        for(Card card : cards) {
            System.out.println("Card: " + card.getName() + ", " + card.getCost() + ", " + Arrays.toString(card.getForces()));
        }
        System.out.println("Deck: " + Arrays.toString(dbConnector.getDeck(6)));

        forceList = dbConnector.getAllForces();
        for(Map.Entry<String, Force[]> entry : forceList.entrySet()) {
            System.out.println(entry.getKey());
            for(int i = 0; i < GlobalVariables.MAX_LEVEL; i++) {
                switch (entry.getValue()[i].getForceKind()) {
                    case SOLDIER:
                        System.out.println("\tLevel: " + (i + 1) + ", HP = " + ((Soldier) (entry.getValue()[i])).getHP());
                        break;
                    case BUILDING:
                        System.out.println("\tLevel: " + (i + 1) + ", HP = " + ((Building) (entry.getValue()[i])).getHP());
                        break;
                    case SPELL:
                        System.out.println("\tLevel: " + (i + 1) + ", Damage = " + ((Spell) (entry.getValue()[i])).getDamage() + ", Duration = " + ((Spell) (entry.getValue()[i])).getDuration());
                }
            }
        }

        pool1v1Thread = new Thread(gamePool1v1);
        pool1v1Thread.start();
        pool2v2Thread = new Thread(gamePool2v2);
        pool2v2Thread.start();

        cardForces = dbConnector.getAllCardForces();
        System.out.println(cardForces.size());
        System.out.println("Card Forces:");
        for(Map.Entry<String, CardForces> entry : cardForces.entrySet()) {
            System.out.println(entry.getKey());
            int round = 1;
            while (true) {
                ArrayList<CardForce> forces = entry.getValue().getForcesOfRound(round);
                if(forces == null || forces.size() == 0)
                    break;
                for(CardForce force : forces) {
                    System.out.println("\t" + force.getForceName() + "\tround: " + force.getRound() + "\tat " + force.getRelLocation());
                }
                round++;
            }
        }


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
        //listenerThread.interrupt();
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

    public Force getForceInfo(String name, int level) {
        if(forceList == null)
            forceList = dbConnector.getAllForces();
        Force[] forceLevels =forceList.get(name);
        if(forceLevels == null || forceLevels.length <= level)
            return null;
        return forceLevels[level - 1];
    }

    public HashMap<String, Force[]> getForceList() {
        return forceList;
    }

    public HashMap<String , Force> getAllForcesOfLevel(int level) {
        if(level > GlobalVariables.MAX_LEVEL)
            return null;
        HashMap<String, Force> forces = new HashMap<>();
        for(Map.Entry<String, Force[]> entry : forceList.entrySet()) {
            forces.put(entry.getKey(), entry.getValue()[level - 1]);
        }
        return forces;
    }

    public ArrayList<CardForce> getCardForces(String cardName, int round) {
        return cardForces.get(cardName).getForcesOfRound(round);
    }

    public Thread startGame(GameEngine gameEngine) {
        Thread gameThread = new Thread(gameEngine);
        engines.put(gameEngine, gameThread);
        gameThread.start();
        return gameThread;
    }
}
