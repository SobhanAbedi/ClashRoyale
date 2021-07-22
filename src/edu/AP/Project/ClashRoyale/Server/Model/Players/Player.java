package edu.AP.Project.ClashRoyale.Server.Model.Players;

import edu.AP.Project.ClashRoyale.Model.Card;
import edu.AP.Project.ClashRoyale.Server.Model.GameEngine.ForceState;

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
