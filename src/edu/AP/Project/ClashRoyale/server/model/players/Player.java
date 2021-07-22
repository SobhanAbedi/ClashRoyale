package edu.ap.project.clashRoyale.server.model.players;

import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;

import java.util.ArrayList;

public abstract class Player {
    private final String name;
    private final Card[] deck;
    private final int side;
    public Player(String name, Card[] deck, int side) {
        this.name = name;
        this.deck = deck;
        this.side = side;
    }

    public abstract void updatePlayer(ArrayList<ArrayList<ForceState>> stateList);

    public abstract int getLevel();

    public int getSide() {
        return side;
    }
}
