package edu.ap.project.clashRoyale.server.model.gameEngine;

public class PowerModifier {
    private float damageBoost;
    private float speedBoost;
    private float hitSpeedBoost;

    /**
     * Constructor
     */
    public PowerModifier() {
        damageBoost = 0;
        speedBoost = 0;
        hitSpeedBoost = 0;
    }

    /**
     * set modifiers
     * @param damageBoost boost damage
     * @param speedBoost speed boost
     * @param hitSpeedBoost hit speed boost
     */
    public void setModifiers(float damageBoost, float speedBoost, float hitSpeedBoost) {
        this.damageBoost = damageBoost;
        this.speedBoost = speedBoost;
        this.hitSpeedBoost = hitSpeedBoost;
    }

    /**
     * get damage modifire
     * @return damage modifier
     */
    public float getDamageModifier() {
        return damageBoost+1;
    }

    /**
     * get speed modifier
     * @return speed modifier
     */
    public float getSpeedModifier() {
        return speedBoost+1;
    }

    /**
     * get hit speed modifier
     * @return
     */
    public float getHitSpeedModifier() {
        return 1f/(hitSpeedBoost+1f);
    }
}
