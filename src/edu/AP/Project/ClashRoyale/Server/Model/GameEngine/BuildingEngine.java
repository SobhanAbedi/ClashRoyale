package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import edu.AP.Project.ClashRoyale.Model.Forces.Building;
import edu.AP.Project.ClashRoyale.Model.Forces.Soldier;

import java.awt.*;

public class BuildingEngine extends ForceEngine{
    private Building referenceBuilding;
    private BuildingState buildingState;
    private BuildingState nextState;
    private PowerModifier modifier;
    private ForceEngine target;
    private float lastAttackTime;
    private float targetMinDist;


    public BuildingEngine(GameEngine gameEngine, Building referenceBuilding, Point location) {
        super(gameEngine, 0.75f);
        this.referenceBuilding = referenceBuilding;
        target = null;
        buildingState = new BuildingState(referenceBuilding.getName(), forceID, location, 90, referenceBuilding.getHP(), ActionKind.CREATE);
        lastAttackTime = -1;
        targetMinDist = 0;
        modifier = new PowerModifier();
    }

    public float getAttackTime() {
        return referenceBuilding.getHitSpeed() * modifier.getHitSpeedModifier();
    }

    public float getDamage() {
        return referenceBuilding.getDamage() * modifier.getDamageModifier();
    }

    @Override
    public void genNextState() {
        try {
            nextState = (BuildingState) buildingState.clone();
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
        buildingState = nextState;
    }

    @Override
    public void doAction(float time) {
        if(isDead())
            gameEngine.removeForce(forceID);
        if(buildingState.getActionKind() == ActionKind.ATTACK) {
            if (target == null) {
                findTarget();
            }
            if(target instanceof SoldierEngine && ((SoldierEngine) target).isDead() || target instanceof BuildingEngine && ((BuildingEngine) target).isDead())
                findTarget();

            setAngle();
        }

        if(buildingState.getActionKind() == ActionKind.ATTACK) {
            if(target.getLocation().distance(getLocation()) - targetMinDist > referenceBuilding.getRange() * 1.3){
                nextState.setActionKind(ActionKind.MOVE);
            }
            if(lastAttackTime == -1 || time - lastAttackTime > getAttackTime()) {
                lastAttackTime = time;
                //TODO: create Projectile with appropriate damage and target
            }
        } else if (target.getLocation().distance(getLocation()) - targetMinDist < referenceBuilding.getRange()) {
            nextState.setActionKind(ActionKind.ATTACK);
        } else if (buildingState.getActionKind() == ActionKind.CREATE) {
            nextState.setActionKind(ActionKind.MOVE);
        }
    }

    @Override
    public Point getLocation() {
        return buildingState.getLocation();
    }

    @Override
    public boolean isSoldierOrBuilding() {
        return true;
    }

    private void findTarget() {
        //TODO: find target
        setTargetMinDist();
    }

    private void setTargetMinDist() {
        targetMinDist = radius + target.getRadius();
    }

    private void setAngle() {
        if(target == null)
            return;
        Point direction = ForceEngine.pointCombination(target.getLocation(), getLocation(), true);
        nextState.setAngle((float) Math.atan2(direction.y, direction.x));
    }

    public boolean isDead() {
        return buildingState.getActionKind() == ActionKind.DEAD || buildingState.getActionKind() == ActionKind.DIE;
    }

    public void acceptDamage(float damage) {
        float finalHP = nextState.getHP() - damage;
        if(finalHP <= 0) {
            kill();
            nextState.setHP(0);
        } else {
            nextState.setHP(finalHP);
        }
    }

    public void kill() {
        nextState.setActionKind(ActionKind.DIE);
    }
}
