package edu.AP.Project.ClashRoyale.Model.Forces;

public class Soldier extends Force{
    private int HP;
    private int damage;
    private float hitSpeed;
    private SpeedTier speed;
    private TargetKind target;
    private float range;
    private boolean areaSplash;

    public Soldier(String name, int HP, int damage, float hitSpeed, SpeedTier speed, TargetKind target, float range, boolean areaSplash) {
        super(name);
        this.HP = HP;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.speed = speed;
        this.target = target;
        this.range = range;
        this.areaSplash = areaSplash;
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
}
