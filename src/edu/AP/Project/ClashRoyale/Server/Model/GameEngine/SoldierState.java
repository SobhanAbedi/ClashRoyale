package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import edu.AP.Project.ClashRoyale.Model.PointDouble;

import java.awt.*;
import java.awt.geom.Point2D;

public class SoldierState extends ForceState implements Cloneable{
    private float angle;
    private float HP;
    private ActionKind actionKind;

    public SoldierState(String forceName, int forceID, PointDouble location, float angle, float HP, ActionKind actionKind) {
        super(forceName, forceID, location);
        this.angle = angle;
        this.HP = HP;
        this.actionKind = actionKind;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new SoldierState(forceName, forceID, new PointDouble(location), angle, HP, ActionKind.values()[actionKind.ordinal()]);
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
