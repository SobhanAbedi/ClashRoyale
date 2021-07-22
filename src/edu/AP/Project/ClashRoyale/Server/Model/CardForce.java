package edu.AP.Project.ClashRoyale.Server.Model;

import edu.AP.Project.ClashRoyale.Model.PointDouble;

public class CardForce {
    private String forceName;
    private PointDouble relLocation;
    private int round;

    public CardForce(String forceName, PointDouble relLocation, int round){
        this.forceName = forceName;
        this.relLocation = relLocation;
        this.round = round;
    }

    public String getForceName() {
        return forceName;
    }

    public PointDouble getRelLocation() {
        return relLocation;
    }

    public int getRound() {
        return round;
    }
}