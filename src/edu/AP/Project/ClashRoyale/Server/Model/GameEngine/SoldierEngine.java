package edu.AP.Project.ClashRoyale.Server.Model.GameEngine;

import edu.AP.Project.ClashRoyale.Model.Forces.Building;
import edu.AP.Project.ClashRoyale.Model.Forces.Soldier;
import edu.AP.Project.ClashRoyale.Model.GlobalVariables;

import java.awt.*;
import java.util.ArrayList;

public class SoldierEngine extends ForceEngine{
    private Soldier referenceSoldier;
    private SoldierState soldierState;
    private SoldierState nextState;
    private PowerModifier modifier;
    private ForceEngine target;
    private Point direction;
    private boolean recheckTarget;
    private float lastAttackTime;
    private float targetMinDist;

    public SoldierEngine(GameEngine gameEngine, Soldier referenceSoldier, Point initialLocation) {
        super(gameEngine, 0.5f);
        this.referenceSoldier = referenceSoldier;
        target = null;
        recheckTarget = false;
        soldierState = new SoldierState(referenceSoldier.getName(), forceID, initialLocation, 90, referenceSoldier.getHP(), ActionKind.CREATE);
        lastAttackTime = -1;
        targetMinDist = 0;
        modifier = new PowerModifier();
        direction = new Point(0, 0);
    }

    public float getSpeed() {
        return referenceSoldier.getHitSpeed() * modifier.getSpeedModifier();
    }

    public float getAttackTime() {
        return referenceSoldier.getHitSpeed() * modifier.getHitSpeedModifier();
    }

    public float getDamage() {
        return referenceSoldier.getDamage() * modifier.getDamageModifier();
    }

    @Override
    public void genNextState() {
        try {
            nextState = (SoldierState) soldierState.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Couldn't Clone the state: " + e.toString());
        }
    }

    @Override
    public SoldierState getNextState() {
        return nextState;
    }

    @Override
    public void next() {
        soldierState = nextState;
    }

    @Override
    public Point getLocation() {
        return soldierState.getLocation();
    }

    @Override
    public boolean isSoldierOrBuilding() {
        return true;
    }

    @Override
    public void doAction(float time) {
        direction.setLocation(0, 0);
        if(isDead())
            gameEngine.removeForce(forceID);
        if(soldierState.getActionKind() == ActionKind.MOVE || soldierState.getActionKind() == ActionKind.ATTACK) {
            if (target == null) {
                findTarget();
            } else if (recheckTarget && soldierState.getActionKind() == ActionKind.MOVE) {
                findTarget();
            }
            if(target instanceof SoldierEngine && ((SoldierEngine) target).isDead() || target instanceof BuildingEngine && ((BuildingEngine) target).isDead())
                findTarget();

            findDirection();
        }

        //check collision no matter what action kind
        ForceEngine[] currentForces = gameEngine.getCurrentForces();
        for(ForceEngine force : currentForces) {
            if(force.forceID == forceID)
                continue;
            if(!force.isSoldierOrBuilding())
                continue;
            Point forceDeltaLocation = ForceEngine.pointCombination(force.getLocation(), getLocation(), true);
            double minDist = radius + force.getRadius();
            if(minDist > ForceEngine.PointLength(forceDeltaLocation)) {
                if(force instanceof SoldierEngine) {
                    ForceEngine.scalePoint(forceDeltaLocation, 0.5);
                }
                translateDirection(forceDeltaLocation, true);
            }
        }

        if(soldierState.getActionKind() == ActionKind.ATTACK) {
            if(target.getLocation().distance(getLocation()) - targetMinDist > referenceSoldier.getRange() * 1.3){
                nextState.setActionKind(ActionKind.MOVE);
            }
            if(lastAttackTime == -1 || time - lastAttackTime > getAttackTime()) {
                lastAttackTime = time;
                if(referenceSoldier.getProjectile() == 0) {
                    doDamage(target, referenceSoldier);
                } else {
                    //TODO: create Projectile with appropriate damage and target
                }
            }
        } else if (target.getLocation().distance(getLocation()) - targetMinDist < referenceSoldier.getRange()) {
            nextState.setActionKind(ActionKind.ATTACK);
        } else if (soldierState.getActionKind() == ActionKind.CREATE) {
            nextState.setActionKind(ActionKind.MOVE);
        }
        nextState.translateLocation(direction, false);
    }

    public void translateDirection(Point translation, boolean negate) {
        if(negate)
            direction.translate(-translation.x, -translation.y);
        else
            direction.translate(translation.x, translation.y);
    }

    private void findTarget() {
        //TODO: find target
        setTargetMinDist();
    }

    private void setTargetMinDist() {
        targetMinDist = radius + target.getRadius();
    }

    public boolean isDead() {
        return soldierState.getActionKind() == ActionKind.DEAD || soldierState.getActionKind() == ActionKind.DIE;
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

    private void findDirection() {
        if(Math.abs(getLocation().y) > 1 && target.getLocation().y * getLocation().y < 0) {
            float averageX = (target.getLocation().x + getLocation().x) / 2f;
            float x_sign = Math.signum(averageX);
            if(x_sign == 0)
                x_sign = 1;
            float y_sign = Math.signum(getLocation().y);
            direction.setLocation(x_sign * GlobalVariables.BRIDGE_X, y_sign);
        } else {
            direction.setLocation(target.getLocation());
        }
        translateDirection(getLocation(), true);
        if(direction.distance(0, 0) > getSpeed() * deltaTime) {
            ForceEngine.normalizePoint(direction);
            ForceEngine.scalePoint(direction, getSpeed() * deltaTime);
        }
        nextState.setAngle((float) Math.atan2(direction.y, direction.x));
    }
}
