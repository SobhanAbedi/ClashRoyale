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

    /**
     * Constructor
     * @param gameEngine game engine
     * @param side side
     * @param referenceSoldier reference soldier
     * @param initialLocation initial location
     */
    public SoldierEngine(GameEngine gameEngine, int side, Soldier referenceSoldier, PointDouble initialLocation) {
        super(gameEngine, 0.5f, side);
        this.referenceSoldier = referenceSoldier;
        target = null;
        recheckTarget = false;
        soldierState = new SoldierState(referenceSoldier.getName(), forceID, initialLocation, 90, referenceSoldier.getHP(), ActionKind.CREATE);
        timeSinceLastAttack = getAttackTime();
        targetMinDist = 0;
        modifier = new PowerModifier();
        direction = new PointDouble(0, 0);
    }

    /**
     * get speed
     * @return speed
     */
    public float getSpeed() {
        return referenceSoldier.getHitSpeed() * modifier.getSpeedModifier();
    }

    /**
     * get attack time
     * @return attack time
     */
    public float getAttackTime() {
        return referenceSoldier.getHitSpeed() * modifier.getHitSpeedModifier();
    }

    /**
     * get damage
     * @return
     */
    public float getDamage() {
        return referenceSoldier.getDamage() * modifier.getDamageModifier();
    }

    /**
     * set modifier
     * @param damageBoost damage boost
     * @param speedBoost speed boost
     * @param hitSpeedBoost hit speed
     */
    public void setModifier(float damageBoost, float speedBoost, float hitSpeedBoost) {
        modifier.setModifiers(damageBoost, speedBoost, hitSpeedBoost);
    }

    /**
     * set modifier zero
     */
    public void setModifierZero() {
        modifier.setModifiers(0, 0, 0);
    }

    /**
     * generate next state
     */
    @Override
    public void genNextState() {
        try {
            nextState = (SoldierState) soldierState.clone();
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
    public SoldierState getNextState() {
        return nextState;
    }

    /**
     * get state
     * @return state
     */
    @Override
    public ForceState getState() {
        return soldierState;
    }

    /**
     * set next state as current state
     */
    @Override
    public void next() {
        soldierState = nextState;
    }

    /**
     * get location
     * @return location
     */
    @Override
    public PointDouble getLocation() {
        return soldierState.getLocation();
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
     * do action
     */
    @Override
    public void doAction() {
        direction.setLocation(0, 0);
        if(isDead()) {
            gameEngine.removeForce(forceID);
            nextState.setActionKind(ActionKind.DEAD);
            return;
        }
        timeSinceLastAttack += deltaTime;
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
            if(target.getLocation().distance(getLocation()) - targetMinDist > referenceSoldier.getRange() * 1.3){
                nextState.setActionKind(ActionKind.MOVE);
            }
            if(timeSinceLastAttack > getAttackTime()) {
                timeSinceLastAttack = 0;
                if(referenceSoldier.getProjectile() == 0) {
                    doDamage(target, referenceSoldier);
                } else {
                    PointDouble deltaLocation = ForceEngine.pointCombination(target.getLocation(), getLocation(), true);
                    ForceEngine.scalePoint(deltaLocation, deltaTime/GlobalVariables.PROJECTILE_TIME);
                    ProjectileEngine projectile = new ProjectileEngine(gameEngine, side, referenceSoldier.getProjectile(), getLocation(), target.getForceID(), getDamage(), deltaLocation);
                    gameEngine.addForce(projectile);
                }
            }
        } else if (target.getLocation().distance(getLocation()) - targetMinDist < referenceSoldier.getRange()) {
            nextState.setActionKind(ActionKind.ATTACK);
        } else if (soldierState.getActionKind() == ActionKind.CREATE) {
            nextState.setActionKind(ActionKind.MOVE);
        }
        nextState.translateLocation(direction, false);
    }

    /**
     * translate direction with translation
     * @param translation translate
     * @param negate add or negate
     */
    public void translateDirection(PointDouble translation, boolean negate) {
        if(negate)
            direction.translate(-translation.x, -translation.y);
        else
            direction.translate(translation.x, translation.y);
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
     * set min distance target
     */
    private void setTargetMinDist() {
        targetMinDist = radius + target.getRadius();
    }

    /**
     * is alive or dead
     * @return alive
     */
    public boolean isDead() {
        return soldierState.getActionKind() == ActionKind.DEAD || soldierState.getActionKind() == ActionKind.DIE;
    }

    /**
     * get damage
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

    /**
     * kill
     */
    public void kill() {
        nextState.setActionKind(ActionKind.DIE);
    }

    /**
     * does this soldier fly or not
     * @return Fly state
     */
    public boolean doesFly(){
        return referenceSoldier.doesFly();
    }

    /**
     * can hit possible Target or not
     * @param possibleTarget possible target
     * @return yes or not
     */
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

    /**
     * find direction
     */
    private void findDirection() {
        if(Math.abs(getLocation().y) > 1 && target.getLocation().y * getLocation().y < 0) {
            float averageX = (float) (target.getLocation().x + getLocation().x) / 2f;
            float x_sign = Math.signum(averageX);
            if(x_sign == 0)
                x_sign = 1;
            float y_sign = Math.signum((float) getLocation().y);
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
