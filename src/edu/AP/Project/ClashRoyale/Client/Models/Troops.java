package edu.AP.Project.ClashRoyale.Client.Models;

public class Troops extends Card {
    public Troops(String name, int cost, int level, boolean inDeck, String cardImageAddress,
                  float hitSpeed, Speed speed, Target target,
                  int range, boolean isAreaSplash, int count,
                  int hp, int damage) {
        super(name, cost, level,inDeck, cardImageAddress);
        this.hitSpeed = hitSpeed;
        this.speed = speed;
        this.target = target;
        this.range = range;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
        this.hp = hp;
        this.damage = damage;
    }

    private float hitSpeed;
    private Speed speed;
    private Target target;
    private int range;
    private boolean isAreaSplash;
    private int count;
    private int hp;
    private int damage;

    public float getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(float hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isAreaSplash() {
        return isAreaSplash;
    }

    public void setAreaSplash(boolean areaSplash) {
        isAreaSplash = areaSplash;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
