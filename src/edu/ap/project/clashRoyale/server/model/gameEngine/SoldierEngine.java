package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.forces.Soldier;
import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.PointDouble;

public class SoldierEngine extends ForceEngine{
    private Soldier referenceSoldier;
    private SoldierState soldierState;
    private SoldierState nextState;
    private PowerModifier modifier;
    private ForceEngine target;
    private PointDouble direction;
    private boolean recheckTarget;
    private float timeSinceLastAttack;
    private float targetMinDist;

    public SoldierEngine(GameEngine gameEngine, int side, Soldier referenceSoldier, PointDouble initialLocation) {
        super(gameEngine, 0.5f, side);
        this.referenceSoldier = referenceSoldier;
        target = null;
        recheckTarget = false;
        soldierState = new SoldierState(referenceSoldier.getName(), forceID, initialLocation, 90, referenceSoldier.getHP(), ActionKind.CREATE);
        modifier = new PowerModifier();
        modifier.setModifiers(0, 0, 0);
        timeSinceLastAttack = getAttackTime();
        targetMinDist = 0;
        direction = new PointDouble(0, 0);
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

    public void setModifier(float damageBoost, float speedBoost, float hitSpeedBoost) {
        modifier.setModifiers(damageBoost, speedBoost, hitSpeedBoost);
    }

    public void setModifierZero() {
        modifier.setModifiers(0, 0, 0);
    }

    @Override
    public void genNextState() {
        try {
            nextState = (SoldierState) soldierState.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Couldn't Clone the state: " + e.toString());
        }
        setModifierZero();
    }

    @Override
    public SoldierState getNextState() {
        return nextState;
    }

    @Override
    public ForceState getState() {
        return soldierState;
    }

    @Override
    public void next() {
        soldierState = nextState;
    }

    @Override
    public PointDouble getLocation() {
        return soldierState.getLocation();
    }

    @Override
    public boolean isSoldierOrBuilding() {
        return true;
    }

    @Override
    public boolean doAction() {
        direction.setLocation(0, 0);
        if(isDead()) {
            gameEngine.removeForce(forceID, false);
            nextState.setActionKind(ActionKind.DEAD);
            return false;
        }
        timeSinceLastAttack += deltaTime;
        if(soldierState.getActionKind() == ActionKind.MOVE || soldierState.getActionKind() == ActionKind.ATTACK) {
            if (target == null || target.isDead()) {
                findTarget();
            } else if (recheckTarget && soldierState.getActionKind() == ActionKind.MOVE) {
                findTarget();
            }

            findDirection();
        }

        //check collision no matter what action kind
        ForceEngine[] currentForces = gameEngine.getCurrentForces();
        for(ForceEngine force : currentForces) {
            if(force.forceID == forceID)
                continue;
            if(!force.isSoldierOrBuilding())
                continue;
            PointDouble forceDeltaLocation = ForceEngine.pointCombination(force.getLocation(), getLocation(), true);
            double minDist = radius + force.getRadius();
            if(minDist > ForceEngine.PointLength(forceDeltaLocation)) {
                if(force instanceof SoldierEngine) {
                    ForceEngine.scalePoint(forceDeltaLocation, 0.5);
                }
                translateDirection(forceDeltaLocation, true);
            }
        }

        if(soldierState.getActionKind() == ActionKind.ATTACK) {
            if(target == null || target.getLocation().distance(getLocation()) - targetMinDist > referenceSoldier.getRange() * 1.3){
                nextState.setActionKind(ActionKind.MOVE);
            }
            if(target != null && timeSinceLastAttack > getAttackTime()) {
                timeSinceLastAttack = 0;
                if(referenceSoldier.getProjectile() == 0) {
                    doDamage(target, referenceSoldier);
                } else {
                    PointDouble deltaLocation = ForceEngine.pointCombination(target.getLocation(), getLocation(), true);
                    ForceEngine.scalePoint(deltaLocation, deltaTime/GlobalVariables.PROJECTILE_TIME);
                    ProjectileEngine projectile = new ProjectileEngine(gameEngine, side, referenceSoldier.getProjectile(), getLocation(), target.getForceID(), getDamage(), deltaLocation);
                    //gameEngine.addForce(projectile);
                    gameEngine.addLater(projectile);
                }
            }
        } else if (target != null && target.getLocation().distance(getLocation()) - targetMinDist < referenceSoldier.getRange()) {
            nextState.setActionKind(ActionKind.ATTACK);
        } else if (soldierState.getActionKind() == ActionKind.CREATE) {
            nextState.setActionKind(ActionKind.MOVE);
        }
        nextState.translateLocation(direction, false);
        return true;
    }

    public void translateDirection(PointDouble translation, boolean negate) {
        if(negate)
            direction.translate(-translation.x, -translation.y);
        else
            direction.translate(translation.x, translation.y);
    }

    private void findTarget() {
        ForceEngine[] currentForces = gameEngine.getCurrentForces();
        double minDist = 100;
        ForceEngine possibleTarget = null;
        for(ForceEngine forceItr : currentForces) {
            if(forceItr.isSoldierOrBuilding() && side != forceItr.getSide() && forceItr.getLocation().distance(getLocation()) < minDist && canHit(forceItr))
                possibleTarget = forceItr;
        }
        target = possibleTarget;
        setTargetMinDist();
    }

    private void setTargetMinDist() {
        if(target == null)
            targetMinDist = 2 * radius;
        else
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

    public boolean doesFly(){
        return referenceSoldier.doesFly();
    }

    public boolean canHit(ForceEngine possibleTarget) {
        if(!possibleTarget.isSoldierOrBuilding())
            return false;
        switch (referenceSoldier.getTarget()) {
            case GROUND_AND_AIR:
                return true;
            case AIR:
                if(possibleTarget instanceof SoldierEngine && ((SoldierEngine) possibleTarget).doesFly())
                    return true;
                return false;
            case GROUND:
                if(possibleTarget instanceof BuildingEngine || (possibleTarget instanceof  SoldierEngine && !((SoldierEngine) possibleTarget).doesFly()))
                    return true;
                return false;
            case BUILDINGS:
                if(possibleTarget instanceof BuildingEngine)
                    return true;
                return false;
            default:
                return false;
        }
    }

    private void findDirection() {
        if(!referenceSoldier.doesFly() && Math.abs(getLocation().y) > 1 && target!= null && target.getLocation().y * getLocation().y < 0) {
            float averageX = (float) (target.getLocation().x + getLocation().x) / 2f;
            float x_sign = Math.signum(averageX);
            if(x_sign == 0)
                x_sign = 1;
            float y_sign = Math.signum((float) getLocation().y);
            direction.setLocation(x_sign * GlobalVariables.BRIDGE_X, y_sign);
        } else if(target != null){
            direction.setLocation(target.getLocation());
        }
        if(target != null) {
            translateDirection(getLocation(), true);
            if (direction.distance(0, 0) > getSpeed() * deltaTime) {
                ForceEngine.normalizePoint(direction);
                ForceEngine.scalePoint(direction, getSpeed() * deltaTime);
            }
        } else {
            direction.setLocation(0, 1);
        }
        nextState.setAngle((float) Math.atan2(direction.y, direction.x));
    }
}
