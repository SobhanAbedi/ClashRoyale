package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import edu.AP.Project.ClashRoyale.Server.Model.Players.Player;

public class GameEngine implements Runnable{
    private final int playerCount;
    private final Player[] players;

    public GameEngine(int playerCount, Player[] players) {
        this.playerCount = playerCount;
        this.players = players;
    }

    @Override
    public void run() {

    }

}
