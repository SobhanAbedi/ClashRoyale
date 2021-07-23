package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.PointDouble;

public class ProjectileState extends ForceState implements Cloneable{
    private float angle;

    public ProjectileState(String forceName, int forceID, PointDouble location, float angle, ActionKind actionKind) {
        super(forceName, forceID, location, actionKind);
        this.angle = angle;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new ProjectileState(forceName, forceID, new PointDouble(location), angle, ActionKind.values()[actionKind.ordinal()]);
    }

    public float getAngle() {
        return angle;
    }

    public ActionKind getActionKind() {
        return actionKind;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setActionKind(ActionKind actionKind) {
        this.actionKind = actionKind;
    }
}
