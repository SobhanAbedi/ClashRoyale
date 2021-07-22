package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import edu.AP.Project.ClashRoyale.Model.PointDouble;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class ForceState {
    protected String forceName;
    protected final int forceID;
    protected PointDouble location;

    public ForceState(String forceName, int forceID, PointDouble location) {
        this.forceName = forceName;
        this.forceID = forceID;
        this.location = location;
    }

    public void translateLocation(PointDouble direction, boolean negate) {
        if(negate)
            location.translate(-direction.x, -direction.y);
        else
            location.translate(direction.x, direction.y);
    }

    public PointDouble getLocation() {
        return location;
    }

    public void setLocation(PointDouble location) {
        this.location = location;
    }
}
