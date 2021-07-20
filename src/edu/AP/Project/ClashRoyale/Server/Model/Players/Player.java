package edu.AP.Project.ClashRoyale.Server.Model.Players;

import edu.AP.Project.ClashRoyale.Model.Card;

public class Player {
    private final String name;
    private final Card[] deck;
    private final int side;
    public Player(String name, Card[] deck, int side) {
        this.name = name;
        this.deck = deck;
        this.side = side;
    }
}
