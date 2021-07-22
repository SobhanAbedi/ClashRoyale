package edu.ap.project.clashRoyale.server.model.players;

import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;

import java.util.ArrayList;

public class BotPlayer extends Player{

    /**
     * Constructor
     * @param name name of Bot
     * @param deck deck cards
     * @param side side position
     */
    public BotPlayer(String name, Card[] deck, int side) {
        super(name, deck, side);
    }

    /**
     * update player
     * @param stateList state list to update player
     */
    @Override
    public void updatePlayer(ArrayList<ArrayList<ForceState>> stateList) {

    }

    /**
     * get level of bot player
     * @return level of bot
     */
    @Override
    public int getLevel() {
        //TODO: fix this after you have created Bot model
        return 1;
    }
}
