package edu.ap.project.clashRoyale.server.model.players;

import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;

import java.util.ArrayList;

public abstract class Player {
    private final String name;
    private final Card[] deck;
    private final int side;

    /**
     * Constructor
     * @param name name of player
     * @param deck deck cards
     * @param side side position
     */
    public Player(String name, Card[] deck, int side) {
        this.name = name;
        this.deck = deck;
        this.side = side;
    }

    /**
     * update player state list
     * @param stateList state list to update player
     */
    public abstract void updatePlayer(ArrayList<ArrayList<ForceState>> stateList);

    /**
     * get level
     * @return level
     */
    public abstract int getLevel();

    /**
     * get side of player
     * @return side position
     */
    public int getSide() {
        return side;
    }
}
