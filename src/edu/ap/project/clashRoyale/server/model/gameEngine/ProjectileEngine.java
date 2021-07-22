package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.PointDouble;

public class ProjectileEngine extends ForceEngine{
    private int targetId;
    private float damage;
    private PointDouble deltaLocation;
    private ProjectileState projectileState;
    private ProjectileState nextState;
    private float runningTime;

    /**
     * Constructor
     * @param gameEngine game engine
     * @param side side
     * @param projectileKind projectile kind
     * @param initialLocation initialize modifier
     * @param targetId target ID
     * @param damage damage
     * @param deltaLocation delta location
     */
    public ProjectileEngine(GameEngine gameEngine, int side, int projectileKind, PointDouble initialLocation, int targetId, float damage, PointDouble deltaLocation) {
        super(gameEngine, 0f, side);
        projectileState = new ProjectileState("Projectile_"+projectileKind, forceID, initialLocation, (float) Math.atan2(deltaLocation.y, deltaLocation.x), ActionKind.CREATE);
        this.targetId = targetId;
        this.damage = damage;
        this.deltaLocation = deltaLocation;
        runningTime = 0;
    }

    /**
     * generate next state
     */
    @Override
    public void genNextState() {
        try {
            nextState = (ProjectileState) projectileState.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Couldn't Clone the state: " + e.toString());
        }
    }

    /**
     * get next state
     * @return next state
     */
    @Override
    public ForceState getNextState() {
        return nextState;
    }

    /**
     * get state
     * @return projectile state
     */
    @Override
    public ForceState getState() {
        return projectileState;
    }

    /**
     * next state
     */
    @Override
    public void next() {
        projectileState = nextState;
    }

    /**
     * do action
     */
    @Override
    public void doAction() {
        if(projectileState.getActionKind() == ActionKind.DIE) {
            ForceEngine target = gameEngine.getForce(targetId);
            if(target != null) {
                if(target instanceof SoldierEngine)
                    ((SoldierEngine) target).acceptDamage(damage);
                if(target instanceof BuildingEngine)
                    ((BuildingEngine) target).acceptDamage(damage);
            }
            gameEngine.removeForce(forceID);
        }
        if(projectileState.getActionKind() == ActionKind.CREATE)
            nextState.setActionKind(ActionKind.MOVE);
        runningTime += deltaTime;
        if(runningTime >= GlobalVariables.PROJECTILE_TIME)
            nextState.setActionKind(ActionKind.DIE);
        nextState.setLocation(ForceEngine.pointCombination(getLocation(), deltaLocation, false));
    }

    /**
     * get location
     * @return location
     */
    @Override
    public PointDouble getLocation() {
        return projectileState.getLocation();
    }

    /**
     * is soldier or building
     * @return NO
     */
    @Override
    public boolean isSoldierOrBuilding() {
        return false;
    }
}
