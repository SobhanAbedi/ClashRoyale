package edu.ap.project.clashRoyale.server.model;

import edu.ap.project.clashRoyale.model.PointDouble;

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


