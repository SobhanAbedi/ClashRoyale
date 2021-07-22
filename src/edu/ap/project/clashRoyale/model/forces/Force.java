package edu.ap.project.clashRoyale.model.forces;

import java.io.Serializable;

public abstract class Force implements Serializable {
    protected String name;
    protected ForceKind forceKind;

    /**
     * Constructor
     * @param name name of Force
     * @param forceKind Kind of Force
     */
    public Force(String name, ForceKind forceKind) {
        this.name = name;
        this.forceKind = forceKind;
    }

    /**
     * get name of force
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get force kind
     * @return force kind
     */
    public ForceKind getForceKind() {
        return forceKind;
    }

    /**
     * get damage
     * @return damage
     */
    public abstract int getDamage();
}
