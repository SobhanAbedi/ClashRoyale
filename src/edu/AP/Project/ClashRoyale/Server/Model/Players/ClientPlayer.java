package edu.AP.Project.ClashRoyale.Server.Model.Players;

import edu.AP.Project.ClashRoyale.Model.Card;
import edu.AP.Project.ClashRoyale.Server.Model.ClientHandler;

public class ClientPlayer extends Player{

    private final ClientHandler handler;

    public ClientPlayer(String name, Card[] deck, int side, ClientHandler handler) {
        super(name, deck, side);
        this.handler = handler;
    }
}
