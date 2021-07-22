package edu.ap.project.clashRoyale.client.models;

public class Buildings extends Card {
    public Buildings(String name, int cost, int level, boolean inDeck, String cardImageAddress, float hitSpeed, Target target, float range, float lifetime, int hp, int damage) {
        super(name, cost, level,inDeck, cardImageAddress);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifetime = lifetime;
        this.hp = hp;
        this.damage = damage;
    }

    private float hitSpeed;
    private Target target;
    private float range;
    private float lifetime;
    private int hp;
    private int damage;

    public float getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(float hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getLifetime() {
        return lifetime;
    }

    public void setLifetime(float lifetime) {
        this.lifetime = lifetime;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
