package edu.AP.Project.ClashRoyale.Model.Forces;

public class Building extends Force{
    private int HP;
    private int damage;
    private float hitSpeed;
    private TargetKind target;
    private float range;
    private int lifeTime;
    private int projectile;

    public Building(String name, int HP, int damage, float hitSpeed, TargetKind target, float range, int lifeTime, int projectile) {
        super(name);
        this.HP = HP;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifeTime = lifeTime;
        this.projectile = projectile;
    }

    public Building(String name, int HP, int damage, float hitSpeed, TargetKind target, float range, int lifeTime) {
        this(name, HP, damage, hitSpeed, target, range, lifeTime, 3);
    }

    public int getHP() {
        return HP;
    }

    public int getDamage() {
        return damage;
    }

    public float getHitSpeed() {
        return hitSpeed;
    }

    public float getRange() {
        return range;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public float getHealthGradiant() {
        return -(((float)HP)/((float)lifeTime));
    }
}
