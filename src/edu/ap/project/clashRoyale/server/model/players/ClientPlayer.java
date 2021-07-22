package edu.ap.project.clashRoyale.server.model.players;

import edu.ap.project.clashRoyale.client.models.GameModel;
import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.server.model.ClientHandler;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;

import java.util.ArrayList;

public class ClientPlayer extends Player{

    private final ClientHandler handler;
    private final GameModel gameModel;

    /**
     * Constructor
     * @param name name of player
     * @param deck deck cards
     * @param side side of player
     * @param handler client handler
     * @param gameModel game model
     */
    public ClientPlayer(String name, Card[] deck, int side, ClientHandler handler, GameModel gameModel) {
        super(name, deck, side);
        this.handler = handler;
        this.gameModel = gameModel;
    }

    /**
     * update player
     * @param stateList state list to update player
     */
    public void updatePlayer(ArrayList<ArrayList<ForceState>> stateList) {
        //TODO: connect directly to gameModel and replace stateList
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
