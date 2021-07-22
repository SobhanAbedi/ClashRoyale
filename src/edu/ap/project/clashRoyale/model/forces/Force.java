package edu.ap.project.clashRoyale.model.forces;

import java.io.Serializable;

public abstract class Force implements Serializable {
    protected String name;
    protected ForceKind forceKind;

    public Force(String name, ForceKind forceKind) {
        this.name = name;
        this.forceKind = forceKind;
    }

    public String getName() {
        return name;
    }

    public ForceKind getForceKind() {
        return forceKind;
    }

    public abstract int getDamage();
}
