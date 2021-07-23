package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.forces.Force;
import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.PointDouble;

public abstract class ForceEngine {
    protected GameEngine gameEngine;
    protected final int forceID;
    protected final float radius;
    protected final float deltaTime;
    protected final int side;

    public ForceEngine(GameEngine gameEngine, float radius, int side) {
        this.gameEngine = gameEngine;
        this.forceID = gameEngine.getID();
        this.radius = radius;
        deltaTime = GlobalVariables.DELTA_TIME;
        this.side = side;
    }

    public abstract void genNextState();
    public abstract ForceState getNextState();
    public abstract ForceState getState();
    public abstract void next();
    public abstract boolean doAction();
    public abstract PointDouble getLocation();
    public abstract boolean isSoldierOrBuilding();


    public float getRadius() {
        return radius;
    }

    public static PointDouble pointCombination(PointDouble point1, PointDouble point2, boolean negate) {
        PointDouble ans = new PointDouble(point1);
        if(negate)
            ans.translate(-point2.x, -point2.y);
        else
            ans.translate(point2.x, point2.y);
        return ans;
    }

    public static void normalizePoint(PointDouble point) {
        double length = point.distance(0, 0);
        point.setLocation(point.x/length, point.y/length);
    }

    public static double PointLength(PointDouble point) {
        return point.distance(0, 0);
    }

    public static void scalePoint(PointDouble point, double k) {
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

    public int getForceID() {
        return forceID;
    }

    public int getSide() {
        return side;
    }

    public abstract boolean isDead();
}
