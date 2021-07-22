package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.PointDouble;

public class ProjectileState extends ForceState implements Cloneable{
    private float angle;
    private ActionKind actionKind;

    /**
     * Constructor
     * @param forceName force name
     * @param forceID force id
     * @param location location
     * @param angle angle
     * @param actionKind action kind
     */
    public ProjectileState(String forceName, int forceID, PointDouble location, float angle, ActionKind actionKind) {
        super(forceName, forceID, location);
        this.angle = angle;
        this.actionKind = actionKind;
    }

    /**
     * clone
     * @return clone
     * @throws CloneNotSupportedException throw exception
     */
    @Override
    public Object clone() throws CloneNotSupportedException{
        return new ProjectileState(forceName, forceID, new PointDouble(location), angle, ActionKind.values()[actionKind.ordinal()]);
    }

    /**
     * get angle
     * @return angle
     */
    public float getAngle() {
        return angle;
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
     * set action kind
     * @param actionKind action kind
     */
    public void setActionKind(ActionKind actionKind) {
        this.actionKind = actionKind;
    }
}
