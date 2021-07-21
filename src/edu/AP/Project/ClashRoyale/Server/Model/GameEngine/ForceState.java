package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import java.awt.*;

public abstract class ForceState {
    protected String forceName;
    protected final int forceID;
    protected Point location;

    public ForceState(String forceName, int forceID, Point location) {
        this.forceName = forceName;
        this.forceID = forceID;
        this.location = location;
    }

    public void translateLocation(Point direction, boolean negate) {
        if(negate)
            location.translate(-direction.x, -direction.y);
        else
            location.translate(direction.x, direction.y);
    }
}
