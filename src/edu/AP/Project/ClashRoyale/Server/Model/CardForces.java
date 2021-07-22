package edu.AP.Project.ClashRoyale.Server.Model;

import edu.AP.Project.ClashRoyale.Model.PointDouble;

import java.util.ArrayList;

public class CardForces {
    private String cardName;
    ArrayList<CardForce> forces;

    public CardForces(String cardName) {
        this.cardName = cardName;
        forces = new ArrayList<>();
    }

    public void addForce(String forceName, PointDouble relLocation, int round) {
        forces.add(new CardForce(forceName, relLocation, round));
    }

    public ArrayList<CardForce> getForcesOfRound(int round) {
        ArrayList<CardForce> selected = new ArrayList<>();
        for(CardForce force : forces) {
            if(force.getRound() == round)
                selected.add(force);
        }
        return selected;
    }
}


