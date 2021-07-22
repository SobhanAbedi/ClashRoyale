package edu.ap.project.clashRoyale.server.model;

import edu.ap.project.clashRoyale.model.PointDouble;

import java.util.ArrayList;

public class CardForces {
    private String cardName;
    ArrayList<CardForce> forces;

    /**
     * Constructor
     * @param cardName card name
     */
    public CardForces(String cardName) {
        this.cardName = cardName;
        forces = new ArrayList<>();
    }

    /**
     * add new force to forces
     * @param forceName force name
     * @param relLocation related location
     * @param round force round of new force
     */
    public void addForce(String forceName, PointDouble relLocation, int round) {
        forces.add(new CardForce(forceName, relLocation, round));
    }

    /**
     * get force round
     * @param round round required
     * @return Arraylist of CardForces that has same round
     */
    public ArrayList<CardForce> getForcesOfRound(int round) {
        ArrayList<CardForce> selected = new ArrayList<>();
        for(CardForce force : forces) {
            if(force.getRound() == round)
                selected.add(force);
        }
        return selected;
    }
}


