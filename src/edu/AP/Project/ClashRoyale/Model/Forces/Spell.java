package edu.AP.Project.ClashRoyale.Model.Forces;

public class Spell extends Force{
    private float radius;

    public Spell(String name, float radius) {
        super(name);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }
}
