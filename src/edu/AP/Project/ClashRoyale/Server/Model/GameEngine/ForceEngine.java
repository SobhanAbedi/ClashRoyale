package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import edu.AP.Project.ClashRoyale.Model.Forces.Force;
import edu.AP.Project.ClashRoyale.Model.GlobalVariables;

import java.awt.*;

public abstract class ForceEngine {
    protected GameEngine gameEngine;
    protected final int forceID;
    protected final float radius;
    protected final float deltaTime;

    public ForceEngine(GameEngine gameEngine, float radius) {
        this.gameEngine = gameEngine;
        this.forceID = gameEngine.getID();
        this.radius = radius;
        deltaTime = GlobalVariables.DELTA_TIME;
    }

    public abstract void genNextState();
    public abstract ForceState getNextState();
    public abstract void next();
    public abstract void doAction(float time);
    public abstract Point getLocation();
    public abstract boolean isSoldierOrBuilding();

    public float getRadius() {
        return radius;
    }

    public static Point pointCombination(Point point1, Point point2, boolean negate) {
        Point ans = new Point(point1);
        if(negate)
            ans.translate(-point2.x, -point2.y);
        else
            ans.translate(point2.x, point2.y);
        return ans;
    }

    public static void normalizePoint(Point point) {
        double length = point.distance(0, 0);
        point.setLocation(point.x/length, point.y/length);
    }

    public static double PointLength(Point point) {
        return point.distance(0, 0);
    }

    public static void scalePoint(Point point, double k) {
        point.setLocation(point.x * k, point.y * k);
    }

    public void doDamage(ForceEngine target, Force reference) {
        if(target instanceof SoldierEngine) {
            ((SoldierEngine)target).acceptDamage(reference.getDamage());
        }
        if(target instanceof BuildingEngine) {
            ((BuildingEngine)target).acceptDamage(reference.getDamage());
        }
    }
}
