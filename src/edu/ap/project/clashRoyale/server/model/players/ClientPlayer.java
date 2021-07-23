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
        boardModel.updateStateList(stateList, rebaseStep);
    }

    /**
     * get level of player
     * @return player level
     */
    public int getLevel(){
        return handler.getLevel();
    }

    /**
     * end game
     * @param win win or loose
     */
    public void endGame(boolean win) {
        //TODO: finish win and lose
    }

    /**
     * get client handler of player
     * @return Client handler of player
     */
    public ClientHandler getHandler() {
        return handler;
    }
}
