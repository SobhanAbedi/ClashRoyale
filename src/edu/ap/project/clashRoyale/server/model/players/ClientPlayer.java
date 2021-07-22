package edu.ap.project.clashRoyale.server.model.players;

import edu.ap.project.clashRoyale.client.models.GameModel;
import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.server.model.ClientHandler;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;

import java.util.ArrayList;

public class ClientPlayer extends Player{

    private final ClientHandler handler;
    private final GameModel gameModel;

    public ClientPlayer(String name, Card[] deck, int side, ClientHandler handler, GameModel gameModel) {
        super(name, deck, side);
        this.handler = handler;
        this.gameModel = gameModel;
    }

    public void updatePlayer(ArrayList<ArrayList<ForceState>> stateList) {
        //TODO: connect directly to gameModel and replace stateList
    }

    public int getLevel(){
        return handler.getLevel();
    }

    public void endGame(boolean win) {
        //TODO: finish win and lose
    }

    public ClientHandler getHandler() {
        return handler;
    }
}
