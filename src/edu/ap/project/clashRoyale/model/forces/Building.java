package edu.ap.project.clashRoyale.model.forces;

public class Building extends Force{
    private int HP;
    private int damage;
    private float hitSpeed;
    private TargetKind target;
    private float range;
    private int lifeTime;
    private int projectile;

    /**
     * building Constructor
     * @param name name
     * @param HP HP
     * @param damage damage
     * @param hitSpeed hit speed
     * @param target target
     * @param range range
     * @param lifeTime life time
     * @param projectile projectile
     */
    public Building(String name, int HP, int damage, float hitSpeed, TargetKind target, float range, int lifeTime, int projectile) {
        super(name, ForceKind.BUILDING);
        this.HP = HP;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifeTime = lifeTime;
        this.projectile = projectile;
    }

    /**
     * building overloading
     * @param name name
     * @param HP hp
     * @param damage damage
     * @param hitSpeed hit speed
     * @param target target
     * @param range range
     * @param lifeTime life time
     */
    public Building(String name, int HP, int damage, float hitSpeed, TargetKind target, float range, int lifeTime) {
        this(name, HP, damage, hitSpeed, target, range, lifeTime, 3);
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
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * get hit speed
     * @return hit speed
     */
    public float getHitSpeed() {
        return hitSpeed;
    }

    /**
     * get range
     * @return range
     */
    public float getRange() {
        return range;
    }

    /**
     * get life time
     * @return life time
     */
    public int getLifeTime() {
        return lifeTime;
    }

    /**
     * get health gradient
     * @return health gradient descending
     */
    public float getHealthGradiant() {
        return -(((float)HP)/((float)lifeTime));
    }

    /**
     * get projectile
     * @return projectile
     */
    public int getProjectile() {
        return projectile;
    }

    /**
     * get target
     * @return target
     */
    public TargetKind getTarget() {
        return target;
    }
}
