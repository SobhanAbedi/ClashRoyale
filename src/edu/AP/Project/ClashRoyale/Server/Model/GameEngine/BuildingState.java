package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import java.awt.*;

public class BuildingState extends ForceState implements Cloneable{
    private float angle;
    private float HP;
    private ActionKind actionKind;

    public BuildingState(String forceName, int forceID, Point location, float angle, float HP, ActionKind actionKind) {
        super(forceName, forceID, location);
        this.angle = angle;
        this.HP = HP;
        this.actionKind = actionKind;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new SoldierState(forceName, forceID, new Point(location), angle, HP, ActionKind.values()[actionKind.ordinal()]);
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
