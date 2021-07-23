package edu.ap.project.clashRoyale.model.forces;

public class Spell extends Force{
    private float radius;
    private int damage;
    private float duration;

    /**
     * spell constructor
     * @param name name
     * @param radius radius
     * @param damage damage
     * @param duration duration
     */
    public Spell(String name, float radius, int damage, float duration) {
        super(name, ForceKind.SPELL);
        this.radius = radius;
        this.damage = damage;
        this.duration = duration;
    }

    /**
     * get radius
     * @return radius
     */
    public float getRadius() {
        return radius;
    }

    /**
     * get damage
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * get duration
     * @return duration
     */
    public float getDuration() {
        return duration;
    }
}
