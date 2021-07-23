package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.forces.Spell;
import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.PointDouble;

import java.util.ArrayList;

public class SpellEngine extends ForceEngine{
    private Spell referenceSpell;
    private SpellState spellState;
    private SpellState nextState;
    private float runningTime;
    private boolean effectDone;

    public SpellEngine(GameEngine gameEngine, int side, Spell referenceSpell, PointDouble location) {
        super(gameEngine, referenceSpell.getRadius(), side);
        this.referenceSpell = referenceSpell;
        spellState = new SpellState(referenceSpell.getName(), forceID, location, ActionKind.CREATE);
        runningTime = 0;
        effectDone = false;
    }

    @Override
    public void genNextState() {
        try {
            nextState = (SpellState) spellState.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Couldn't Clone the state: " + e.toString());
        }
    }

    @Override
    public ForceState getNextState() {
        return nextState;
    }

    @Override
    public ForceState getState() {
        return spellState;
    }

    @Override
    public void next() {
        spellState = nextState;
    }

    @Override
    public boolean doAction() {
        if(isDead()) {
            gameEngine.removeForce(forceID, false);
            nextState.setActionKind(ActionKind.DEAD);
            return false;
        }
        if(spellState.getActionKind() == ActionKind.CREATE);
        runningTime += deltaTime;
        switch (referenceSpell.getName()) {
            case "Rage":
                if(runningTime >= referenceSpell.getDuration()) {
                    nextState.setActionKind(ActionKind.DIE);
                }
                ForceEngine[] targets1 = findTargets();
                if(targets1 == null)
                    break;
                for(ForceEngine target : targets1) {
                    if(target instanceof SoldierEngine)
                        ((SoldierEngine) target).setModifier(0.4f, 0.4f, 0.4f);
                    if(target instanceof BuildingEngine)
                        ((BuildingEngine) target).setModifier(0.4f, 0, 0.4f);
                }
                break;
            case "Arrows":
            case "Fireball":
                if(runningTime >= GlobalVariables.INSTANT_SPELLS_TIME) {
                    nextState.setActionKind(ActionKind.DIE);
                }
                if(!effectDone && runningTime<= (GlobalVariables.INSTANT_SPELLS_TIME/2f) && (runningTime+deltaTime) > (GlobalVariables.INSTANT_SPELLS_TIME)) {
                    ForceEngine[] targets2 = findTargets();
                    if(targets2 == null)
                        break;
                    for(ForceEngine target : targets2) {
                        if(target instanceof SoldierEngine)
                            ((SoldierEngine) target).acceptDamage(referenceSpell.getDamage());
                        if(target instanceof BuildingEngine)
                            ((BuildingEngine) target).acceptDamage(referenceSpell.getDamage());
                    }
                }
                break;

        }
        return true;
    }

    @Override
    public PointDouble getLocation() {
        return spellState.getLocation();
    }

    @Override
    public boolean isSoldierOrBuilding() {
        return false;
    }


    private ForceEngine[] findTargets() {
        ForceEngine[] currentForces = gameEngine.getCurrentForces();
        ArrayList<ForceEngine> targets = new ArrayList<>();
        for(ForceEngine forceItr : currentForces) {
            if(forceItr.isSoldierOrBuilding() && forceItr.getLocation().distance(getLocation()) < referenceSpell.getRadius())
                targets.add(forceItr);
        }
        return targets.toArray(new ForceEngine[0]);
    }

    public boolean isDead() {
        return spellState.getActionKind() == ActionKind.DEAD || spellState.getActionKind() == ActionKind.DIE;
    }

}
