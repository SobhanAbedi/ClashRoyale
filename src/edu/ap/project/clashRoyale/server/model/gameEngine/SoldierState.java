package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.PointDouble;

public class SoldierState extends ForceState implements Cloneable{
    private float angle;
    private float HP;
    private ActionKind actionKind;

    /**
     * Constructor
     * @param forceName name of force
     * @param forceID force id
     * @param location location
     * @param angle angle
     * @param HP hp
     * @param actionKind action kind
     */
    public SoldierState(String forceName, int forceID, PointDouble location, float angle, float HP, ActionKind actionKind) {
        super(forceName, forceID, location);
        this.angle = angle;
        this.HP = HP;
        this.actionKind = actionKind;
    }

    /**
     * clone
     * @return soldier state
     * @throws CloneNotSupportedException throw clone exception
     */
    @Override
    public Object clone() throws CloneNotSupportedException{
        return new SoldierState(forceName, forceID, new PointDouble(location), angle, HP, ActionKind.values()[actionKind.ordinal()]);
    }

    /**
     * get angle
     * @return angle
     */
    public float getAngle() {
        return angle;
    }

    /**
     * get HP
     * @return hp
     */
    public float getHP() {
        return HP;
    }

    /**
     * get action kind
     * @return action kind
     */
    public ActionKind getActionKind() {
        return actionKind;
    }

    /**
     * set angle
     * @param angle angle
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * set HP
     * @param HP hp
     */
    public void setHP(float HP) {
        this.HP = HP;
    }

    /**
     * set action kind
     * @param actionKind action kind
     */
    public void setActionKind(ActionKind actionKind) {
        this.actionKind = actionKind;
    }
}
