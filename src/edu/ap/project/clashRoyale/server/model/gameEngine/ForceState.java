package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.PointDouble;

public abstract class ForceState {
    protected String forceName;
    protected final int forceID;
    protected PointDouble location;
    protected ActionKind actionKind;

    public ForceState(String forceName, int forceID, PointDouble location, ActionKind actionKind) {
        this.forceName = forceName;
        this.forceID = forceID;
        this.location = location;
        this.actionKind = actionKind;
    }

    public void translateLocation(PointDouble direction, boolean negate) {
        if(negate)
            location.translate(-direction.x, -direction.y);
        else
            location.translate(direction.x, direction.y);
    }

    public PointDouble getLocation() {
        return location;
    }

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
