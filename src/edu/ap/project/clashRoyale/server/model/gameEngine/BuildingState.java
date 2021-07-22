package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.PointDouble;

public class BuildingState extends ForceState implements Cloneable{
    private float angle;
    private float HP;
    private ActionKind actionKind;

    public BuildingState(String forceName, int forceID, PointDouble location, float angle, float HP, ActionKind actionKind) {
        super(forceName, forceID, location);
        this.angle = angle;
        this.HP = HP;
        this.actionKind = actionKind;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new BuildingState(forceName, forceID, new PointDouble(location), angle, HP, ActionKind.values()[actionKind.ordinal()]);
    }

    public float getAngle() {
        return angle;
    }

    public float getHP() {
        return HP;
    }

    public ActionKind getActionKind() {
        return actionKind;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public void setActionKind(ActionKind actionKind) {
        this.actionKind = actionKind;
    }
}
