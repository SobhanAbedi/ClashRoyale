package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import edu.AP.Project.ClashRoyale.Model.PointDouble;

import java.awt.*;
import java.awt.geom.Point2D;

public class SpellState extends ForceState implements Cloneable{
    private ActionKind actionKind;

    public SpellState(String forceName, int forceID, PointDouble location, ActionKind actionKind) {
        super(forceName, forceID, location);
        this.actionKind = actionKind;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new SpellState(forceName, forceID, new PointDouble(location), ActionKind.values()[actionKind.ordinal()]);
    }

    public ActionKind getActionKind() {
        return actionKind;
    }

    public void setActionKind(ActionKind actionKind) {
        this.actionKind = actionKind;
    }
}
