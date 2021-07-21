package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import edu.AP.Project.ClashRoyale.Model.Forces.Soldier;
import edu.AP.Project.ClashRoyale.Model.GlobalVariables;

import java.awt.*;

public class ProjectileEngine extends ForceEngine{
    private int targetId;
    private float damage;
    private Point deltaLocation;
    private ProjectileState projectileState;
    private ProjectileState nextState;
    private float runningTime;

    public ProjectileEngine(GameEngine gameEngine, int side, int projectileKind, Point initialLocation, int targetId, float damage, Point deltaLocation) {
        super(gameEngine, 0f, side);
        projectileState = new ProjectileState("Projectile_"+projectileKind, forceID, initialLocation, (float) Math.atan2(deltaLocation.y, deltaLocation.x), ActionKind.CREATE);
        this.targetId = targetId;
        this.damage = damage;
        this.deltaLocation = deltaLocation;
        runningTime = 0;
    }


    @Override
    public void genNextState() {
        try {
            nextState = (ProjectileState) projectileState.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Couldn't Clone the state: " + e.toString());
        }
    }

    @Override
    public ForceState getNextState() {
        return nextState;
    }

    @Override
    public void next() {
        projectileState = nextState;
    }

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

    @Override
    public Point getLocation() {
        return projectileState.getLocation();
    }

    @Override
    public boolean isSoldierOrBuilding() {
        return false;
    }
}
