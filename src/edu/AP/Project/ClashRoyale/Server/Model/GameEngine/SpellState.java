package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import java.awt.*;

public class SpellState extends ForceState implements Cloneable{
    private ActionKind actionKind;

    public SpellState(String forceName, int forceID, Point location, ActionKind actionKind) {
        super(forceName, forceID, location);
        this.actionKind = actionKind;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new SpellState(forceName, forceID, new Point(location), ActionKind.values()[actionKind.ordinal()]);
    }

    public ActionKind getActionKind() {
        return actionKind;
    }

    public void setActionKind(ActionKind actionKind) {
        this.actionKind = actionKind;
    }
}
