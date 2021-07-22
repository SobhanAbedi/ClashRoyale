package edu.ap.project.clashRoyale.model;

import java.awt.geom.Point2D;

public class PointDouble extends Point2D.Double {

    public PointDouble(PointDouble point) {
        x = point.x;
        y = point.y;
    }

    public PointDouble(double newX, double newY) {
        x = newX;
        y = newY;
    }

    public PointDouble(float newX, float newY) {
        x = newX;
        y = newY;
    }

    public void translate(double dX, double dY) {
        x += dX;
        y += dY;
    }

    public void translate(float dX, float dY) {
        x += dX;
        y += dY;
    }

    public PointDouble combineWith(PointDouble secondPoint, boolean negate) {
        if(negate)
            return new PointDouble(x - secondPoint.x, y - secondPoint.y);
        else
            return new PointDouble(x + secondPoint.x, y + secondPoint.y);
    }

    public PointDouble reverseMapPoint() {
        return new PointDouble(-x, GlobalVariables.QUARTER_LENGTH - y);
    }
}
