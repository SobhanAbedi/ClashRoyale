package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.forces.Building;
import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.PointDouble;

public class BuildingEngine extends ForceEngine{
    private Building referenceBuilding;
    private BuildingState buildingState;
    private BuildingState nextState;
    private PowerModifier modifier;
    private ForceEngine target;
    private float timeSinceLastAttack;
    private float targetMinDist;

    /**
     * Constructor
     * @param gameEngine game engine
     * @param side side
     * @param referenceBuilding building
     * @param location location
     */
    public BuildingEngine(GameEngine gameEngine, int side, Building referenceBuilding, PointDouble location) {
        super(gameEngine, 0.75f, side);
        this.referenceBuilding = referenceBuilding;
        target = null;
        buildingState = new BuildingState(referenceBuilding.getName(), forceID, location, 90, referenceBuilding.getHP(), ActionKind.CREATE);
        modifier = new PowerModifier();
        modifier.setModifiers(0, 0, 0);
        timeSinceLastAttack = getAttackTime();
        targetMinDist = 0;
    }

    /**
     * get attack Time
     * @return attack time
     */
    public float getAttackTime() {
        return referenceBuilding.getHitSpeed() * modifier.getHitSpeedModifier();
    }

    /**
     * get damage
     * @return damage
     */
    public float getDamage() {
        return referenceBuilding.getDamage() * modifier.getDamageModifier();
    }

    /**
     * set modifier
     * @param damageBoost boost damage
     * @param speedBoost speed boost
     * @param hitSpeedBoost boost hit speed
     */
    public void setModifier(float damageBoost, float speedBoost, float hitSpeedBoost) {
        modifier.setModifiers(damageBoost, speedBoost, hitSpeedBoost);
    }

    /**
     * set modifier as zero
     */
    public void setModifierZero() {
        modifier.setModifiers(0, 0, 0);
    }

    /**
     * next state generation
     */
    @Override
    public void genNextState() {
        try {
            nextState = (BuildingState) buildingState.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Couldn't Clone the state: " + e.toString());
        }
        setModifierZero();
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
     * @return building state
     */
    @Override
    public ForceState getState() {
        return buildingState;
    }

    /**
     * set next state as state
     */
    @Override
    public void next() {
        buildingState = nextState;
    }

    /**
     * do action
     */
    @Override
    public boolean doAction() {
        if(isDead()) {
            gameEngine.removeForce(forceID, false);
            nextState.setActionKind(ActionKind.DEAD);
            return false;
        }
        timeSinceLastAttack += deltaTime;
        if(buildingState.getActionKind() == ActionKind.ATTACK) {
            if (target == null) {
                findTarget();
            }
            if(target.isDead())
                findTarget();

            setAngle();
        }

        if(buildingState.getActionKind() == ActionKind.ATTACK) {
            if(target == null || target.getLocation().distance(getLocation()) - targetMinDist > referenceBuilding.getRange() * 1.3){
                nextState.setActionKind(ActionKind.MOVE);
            }
            if(target != null && timeSinceLastAttack > getAttackTime()) {
                timeSinceLastAttack = 0;
                PointDouble deltaLocation = ForceEngine.pointCombination(target.getLocation(), getLocation(), true);
                ForceEngine.scalePoint(deltaLocation, deltaTime/ GlobalVariables.PROJECTILE_TIME);
                ProjectileEngine projectile = new ProjectileEngine(gameEngine, side, referenceBuilding.getProjectile(), getLocation(), target.getForceID(), getDamage(), deltaLocation);
                //gameEngine.addForce(projectile);
                gameEngine.addLater(projectile);
            }
        } else if (target != null && target.getLocation().distance(getLocation()) - targetMinDist < referenceBuilding.getRange()) {
            nextState.setActionKind(ActionKind.ATTACK);
        } else if (buildingState.getActionKind() == ActionKind.CREATE) {
            if(nextState == null)
                System.out.println("**NextState is null");
            nextState.setActionKind(ActionKind.MOVE);
        }
        if(referenceBuilding.getLifeTime() != 0)
            acceptDamage(-referenceBuilding.getHealthGradiant()*deltaTime);
        return true;
    }

    /**
     * get building location
     * @return location
     */
    @Override
    public PointDouble getLocation() {
        return buildingState.getLocation();
    }

    /**
     * is soldier or building
     * @return yes
     */
    @Override
    public boolean isSoldierOrBuilding() {
        return true;
    }

    /**
     * find target
     */
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

    /**
     * can hit to possible target
     * @param possibleTarget possible target
     * @return find possible target
     */
    public boolean canHit(ForceEngine possibleTarget) {
        if(!possibleTarget.isSoldierOrBuilding())
            return false;
        switch (referenceBuilding.getTarget()) {
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

    /**
     * set min distance to target
     */
    private void setTargetMinDist() {
        targetMinDist = radius + target.getRadius();
    }

    /**
     * set angle
     */
    private void setAngle() {
        if(target == null)
            return;
        PointDouble direction = ForceEngine.pointCombination(target.getLocation(), getLocation(), true);
        nextState.setAngle((float) Math.atan2(direction.y, direction.x));
    }

    /**
     * is Dead?
     * @return dead or not
     */
    public boolean isDead() {
        return buildingState.getActionKind() == ActionKind.DEAD || buildingState.getActionKind() == ActionKind.DIE;
    }

    /**
     * accept damage
     * @param damage damage amount
     */
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
