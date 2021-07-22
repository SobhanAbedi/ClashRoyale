package edu.ap.project.clashRoyale.server.model.players;

import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.server.model.gameEngine.ForceState;

import java.util.ArrayList;

public class BotPlayer extends Player{

    public BotPlayer(String name, Card[] deck, int side) {
        super(name, deck, side);
    }

    @Override
    public void updatePlayer(ArrayList<ArrayList<ForceState>> stateList, int rebaseStep) {

    }

    @Override
    public int getLevel() {
        //TODO: fix this after you have created Bot model
        return 1;
    }
}
