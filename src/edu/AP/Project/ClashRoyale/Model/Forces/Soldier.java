package edu.AP.Project.ClashRoyale.Model.Forces;

public class Soldier extends Force{
    private int HP;
    private int damage;
    private float hitSpeed;
    private SpeedTier speed;
    private TargetKind target;
    private float range;
    private boolean areaSplash;
    private boolean flies;
    private int projectile;

    public Soldier(String name, int HP, int damage, float hitSpeed, SpeedTier speed, TargetKind target, float range, boolean areaSplash, boolean flies, int projectile) {
        super(name, ForceKind.SOLDIER);
        this.HP = HP;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.speed = speed;
        this.target = target;
        this.range = range;
        this.areaSplash = areaSplash;
        this.flies = flies;
        this.projectile = projectile;
    }

    public Soldier(String name, int HP, int damage, float hitSpeed, SpeedTier speed, TargetKind target, float range, boolean areaSplash) {
        this(name, HP, damage, hitSpeed, speed, target, range, areaSplash, false, 0);
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

    public SpeedTier getSpeed() {
        return speed;
    }

    public TargetKind getTarget() {
        return target;
    }

    public float getRange() {
        return range;
    }

    public boolean isAreaSplash() {
        return areaSplash;
    }

    public int getProjectile() {
        return projectile;
    }

    public boolean doesFly() {
        return flies;
    }
}
