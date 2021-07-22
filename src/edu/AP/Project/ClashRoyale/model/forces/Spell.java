package edu.ap.project.clashRoyale.model.forces;

public class Spell extends Force{
    private float radius;
    private int damage;
    private float duration;

    public Spell(String name, float radius, int damage, float duration) {
        super(name, ForceKind.SPELL);
        this.radius = radius;
        this.damage = damage;
        this.duration = duration;
    }

    public float getRadius() {
        return radius;
    }

    public int getDamage() {
        return damage;
    }

    public float getDuration() {
        return duration;
    }
}
