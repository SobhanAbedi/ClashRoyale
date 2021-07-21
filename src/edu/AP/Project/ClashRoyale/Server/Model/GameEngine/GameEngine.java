package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import edu.AP.Project.ClashRoyale.Server.Model.Players.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class GameEngine implements Runnable{
    private final int playerCount;
    private final Player[] players;
    private int lastForceID;
    private HashMap<Integer, ForceEngine> currentForces;
    private HashMap<Integer, ForceEngine> deadForces;

    public GameEngine(int playerCount, Player[] players) {
        this.playerCount = playerCount;
        this.players = players;
        lastForceID = 0;
    }

    public int getID() {
        lastForceID++;
        return lastForceID;
    }

    public ForceEngine[] getCurrentForces() {
        return (ForceEngine[]) currentForces.values().toArray();
    }

    public void removeForce(int forceID) {
        deadForces.put(forceID, currentForces.get(forceID));
        currentForces.remove(forceID);
        //TODO:Check if a king or princess tower is hig
    }

    @Override
    public void run() {

    }

}
