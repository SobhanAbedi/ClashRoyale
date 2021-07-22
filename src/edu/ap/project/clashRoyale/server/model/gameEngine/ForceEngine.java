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

    /**
     * Force engine
     * @param gameEngine game engine
     * @param radius radius
     * @param side side
     */
    public ForceEngine(GameEngine gameEngine, float radius, int side) {
        this.gameEngine = gameEngine;
        this.forceID = gameEngine.getID();
        this.radius = radius;
        deltaTime = GlobalVariables.DELTA_TIME;
        this.side = side;
    }

    /**
     * generate next state
     */
    public abstract void genNextState();

    /**
     * generate next state
     * @return Force state
     */
    public abstract ForceState getNextState();

    /**
     * get state
     * @return force state
     */
    public abstract ForceState getState();

    /**
     * process next
     */
    public abstract void next();

    /**
     * do action
     */
    public abstract void doAction();

    /**
     * get location
     * @return location
     */
    public abstract PointDouble getLocation();

    /**
     * is it soldier or building?
     * @return building or soldier
     */
    public abstract boolean isSoldierOrBuilding();

    /**
     * get radius
     * @return
     */
    public float getRadius() {
        return radius;
    }

    /**
     * add or negate two points
     * @param point1 point one
     * @param point2 point two
     * @param negate negate or add
     * @return result
     */
    public static PointDouble pointCombination(PointDouble point1, PointDouble point2, boolean negate) {
        PointDouble ans = new PointDouble(point1);
        if(negate)
            ans.translate(-point2.x, -point2.y);
        else
            ans.translate(point2.x, point2.y);
        return ans;
    }

    /**
     * normalize point
     * @param point point to be normalized
     */
    public static void normalizePoint(PointDouble point) {
        double length = point.distance(0, 0);
        point.setLocation(point.x/length, point.y/length);
    }

    /**
     * point length
     * @param point point to calculate its length to 0 , 0
     * @return
     */
    public static double PointLength(PointDouble point) {
        return point.distance(0, 0);
    }

    /**
     * scale point with k
     * @param point input point
     * @param k scale factor
     */
    public static void scalePoint(PointDouble point, double k) {
        point.setLocation(point.x * k, point.y * k);
    }

    /**
     * do damage
     * @param target target to attack
     * @param reference reference force
     */
    public void doDamage(ForceEngine target, Force reference) {
        if(target instanceof SoldierEngine) {
            ((SoldierEngine)target).acceptDamage(reference.getDamage());
        }
        if(target instanceof BuildingEngine) {
            ((BuildingEngine)target).acceptDamage(reference.getDamage());
        }
    }

    /**
     * get force id
     * @return force id
     */
    public int getForceID() {
        return forceID;
    }

    /**
     * get side
     * @return side
     */
    public int getSide() {
        return side;
    }
}
