package edu.AP.Project.ClashRoyale.Server.Model.Players;

import edu.AP.Project.ClashRoyale.Client.Models.GameModel;
import edu.AP.Project.ClashRoyale.Model.Card;
import edu.AP.Project.ClashRoyale.Server.Model.ClientHandler;
import edu.AP.Project.ClashRoyale.Server.Model.GameEngine.ForceState;

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
