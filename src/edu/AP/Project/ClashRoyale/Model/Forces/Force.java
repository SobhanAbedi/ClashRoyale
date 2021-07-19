package edu.AP.Project.ClashRoyale.Model.Forces;

import java.io.Serializable;

public abstract class Force implements Serializable {
    protected String name;

    public Force(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
