package edu.ap.project.clashRoyale.model;

import java.awt.geom.Point2D;

public class PointDouble extends Point2D.Double {

    /**
     * Constructor
     * @param point point
     */
    public PointDouble(PointDouble point) {
        x = point.x;
        y = point.y;
    }

    /**
     * Constructor overloading
     * @param newX new x double
     * @param newY new y double
     */
    public PointDouble(double newX, double newY) {
        x = newX;
        y = newY;
    }

    /**
     * Constructor Overloading
     * @param newX new x float
     * @param newY new y float
     */
    public PointDouble(float newX, float newY) {
        x = newX;
        y = newY;
    }

    /**
     * translate with dx,dy
     * @param dX x movement
     * @param dY y movement
     */
    public void translate(double dX, double dY) {
        x += dX;
        y += dY;
    }

    /**
     * translate with dx, dy
     * @param dX x movement
     * @param dY y movement
     */
    public void translate(float dX, float dY) {
        x += dX;
        y += dY;
    }

    /**
     * combine two pointDouble
     * @param secondPoint second Point
     * @param negate add or negate
     * @return new point double
     */
    public PointDouble combineWith(PointDouble secondPoint, boolean negate) {
        if(negate)
            return new PointDouble(x - secondPoint.x, y - secondPoint.y);
        else
            return new PointDouble(x + secondPoint.x, y + secondPoint.y);
    }

    /**
     * reverse point in Map
     * @return reversed point
     */
    public PointDouble reverseMapPoint() {
        return new PointDouble(-x,  - y);
    }
}
