package edu.AP.Project.ClashRoyale.Server.Model.Players;

import edu.AP.Project.ClashRoyale.Model.Card;
import edu.AP.Project.ClashRoyale.Server.Model.GameEngine.ForceState;

import java.util.ArrayList;

public class BotPlayer extends Player{

    public BotPlayer(String name, Card[] deck, int side) {
        super(name, deck, side);
    }

    @Override
    public void updatePlayer(ArrayList<ArrayList<ForceState>> stateList) {

    }

    @Override
    public int getLevel() {
        //TODO: fix this after you have created Bot model
        return 1;
    }
}
