package edu.ap.project.clashRoyale.server.model.players;

import edu.ap.project.clashRoyale.client.models.BoardModel;
import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.server.model.ClientHandler;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;

import java.util.ArrayList;

public class ClientPlayer extends Player{

    private final ClientHandler handler;
    private final BoardModel boardModel;

    public ClientPlayer(String name, Card[] deck, int side, ClientHandler handler, BoardModel boardModel) {
        super(name, deck, side);
        this.handler = handler;
        this.boardModel = boardModel;
    }

    public void updatePlayer(ArrayList<ArrayList<ForceState>> stateList, int rebaseStep) {
        //TODO: connect directly to gameModel and replace stateList
        boardModel.updateStateList(stateList, rebaseStep);
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
