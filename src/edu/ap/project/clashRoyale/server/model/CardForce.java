package edu.ap.project.clashRoyale.server.model;

import edu.ap.project.clashRoyale.model.PointDouble;

public class CardForce {
    private String forceName;
    private PointDouble relLocation;
    private int round;

    /**
     * Constructor
     * @param forceName force name
     * @param relLocation related location
     * @param round round
     */
    public CardForce(String forceName, PointDouble relLocation, int round){
        this.forceName = forceName;
        this.relLocation = relLocation;
        this.round = round;
    }

    /**
     * getter for force name
     * @return force name
     */
    public String getForceName() {
        return forceName;
    }

    /**
     * get related location
     * @return related location of force
     */
    public PointDouble getRelLocation() {
        return relLocation;
    }

    /**
     * get round
     * @return round
     */
    public int getRound() {
        return round;
    }
}