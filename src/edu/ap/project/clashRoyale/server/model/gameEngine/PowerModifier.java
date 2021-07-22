package edu.ap.project.clashRoyale.server.model.gameEngine;

public class PowerModifier {
    private float damageBoost;
    private float speedBoost;
    private float hitSpeedBoost;

    public PowerModifier() {
        damageBoost = 0;
        speedBoost = 0;
        hitSpeedBoost = 0;
    }

    public void setModifiers(float damageBoost, float speedBoost, float hitSpeedBoost) {
        this.damageBoost = damageBoost;
        this.speedBoost = speedBoost;
        this.hitSpeedBoost = hitSpeedBoost;
    }

    public float getDamageModifier() {
        return damageBoost+1;
    }

    public float getSpeedModifier() {
        return speedBoost+1;
    }

    public float getHitSpeedModifier() {
        return 1f/(hitSpeedBoost+1f);
    }
}
