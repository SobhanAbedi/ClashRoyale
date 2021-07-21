package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import java.awt.*;

public class ProjectileState extends ForceState implements Cloneable{
    private float angle;
    private ActionKind actionKind;

    public ProjectileState(String forceName, int forceID, Point location, float angle, ActionKind actionKind) {
        super(forceName, forceID, location);
        this.angle = angle;
        this.actionKind = actionKind;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new ProjectileState(forceName, forceID, new Point(location), angle, ActionKind.values()[actionKind.ordinal()]);
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
