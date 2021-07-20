package edu.AP.Project.ClashRoyale.Model.Forces;

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
}
