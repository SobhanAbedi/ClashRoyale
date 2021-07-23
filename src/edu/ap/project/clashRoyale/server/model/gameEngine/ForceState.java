package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.PointDouble;

public abstract class ForceState {
    protected String forceName;
    protected final int forceID;
    protected PointDouble location;
    protected ActionKind actionKind;

    /**
     * Constructor
     * @param forceName force name
     * @param forceID force ID
     * @param location location
     */
    public ForceState(String forceName, int forceID, PointDouble location, ActionKind actionKind) {
        this.forceName = forceName;
        this.forceID = forceID;
        this.location = location;
        this.actionKind = actionKind;
    }

    /**
     * translate location
     * @param direction direction
     * @param negate add or negate
     */
    public void translateLocation(PointDouble direction, boolean negate) {
        if(negate)
            location.translate(-direction.x, -direction.y);
        else
            location.translate(direction.x, direction.y);
    }

    /**
     * get location
     * @return location
     */
    public PointDouble getLocation() {
        return location;
    }

    /**
     * set location
     * @param location location
     */
    public void setLocation(PointDouble location) {
        this.location = location;
    }

    public int getForceID() {
        return forceID;
    }

    public ActionKind getActionKind() {
        return actionKind;
    }

    public float getAngle() {
        return 0;
    }

    public String getForceName() {
        return forceName;
    }
}
