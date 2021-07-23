package edu.ap.project.clashRoyale.model.forces;

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

    /**
     * Constructor
     * @param name name
     * @param HP hp
     * @param damage damage
     * @param hitSpeed hit speed
     * @param speed movement speed
     * @param target target
     * @param range range
     * @param areaSplash is area splash
     * @param flies is flies
     * @param projectile projectile
     */
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
    /**
     * Constructor overloading
     * @param name name
     * @param HP hp
     * @param damage damage
     * @param hitSpeed hit speed
     * @param speed movement speed
     * @param target target
     * @param range range
     * @param areaSplash is area splash
     */
    public Soldier(String name, int HP, int damage, float hitSpeed, SpeedTier speed, TargetKind target, float range, boolean areaSplash) {
        this(name, HP, damage, hitSpeed, speed, target, range, areaSplash, false, 0);
    }

    /**
     * get HP
     * @return HP
     */
    public int getHP() {
        return HP;
    }

    /**
     * get damage
     * @return Damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * get hit Speed
     * @return Hit speed
     */
    public float getHitSpeed() {
        return hitSpeed;
    }

    /**
     * get movement speed
     * @return speed
     */
    public SpeedTier getSpeed() {
        return speed;
    }

    /**
     * get target
     * @return target
     */
    public TargetKind getTarget() {
        return target;
    }

    /**
     * get range
     * @return range
     */
    public float getRange() {
        return range;
    }

    /**
     * is area splash
     * @return area splash
     */
    public boolean isAreaSplash() {
        return areaSplash;
    }

    /**
     * get projectile
     * @return projectile
     */
    public int getProjectile() {
        return projectile;
    }

    /**
     * does it flies?
     * @return fly
     */
    public boolean doesFly() {
        return flies;
    }
}
