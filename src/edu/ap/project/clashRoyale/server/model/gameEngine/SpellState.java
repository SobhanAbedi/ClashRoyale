package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.PointDouble;

public class SpellState extends ForceState implements Cloneable{

    /**
     * Constructor
     * @param forceName force name
     * @param forceID foce ID
     * @param location Location
     * @param actionKind action kind
     */
    public SpellState(String forceName, int forceID, PointDouble location, ActionKind actionKind) {
        super(forceName, forceID, location, actionKind);
    }

    /**
     * clone
     * @return Spell State
     * @throws CloneNotSupportedException throw clone exception
     */
    @Override
    public Object clone() throws CloneNotSupportedException{
        return new SpellState(forceName, forceID, new PointDouble(location), ActionKind.values()[actionKind.ordinal()]);
    }

    /**
     * get action kind
     * @return action kind
     */
    public ActionKind getActionKind() {
        return actionKind;
    }

    /**
     * set action kind
     * @param actionKind
     */
    public void setActionKind(ActionKind actionKind) {
        this.actionKind = actionKind;
    }
}
