package edu.ap.project.clashRoyale.client.models;

public class Spells extends Card {
    public Spells(String name, int cost, int level, boolean inDeck, String cardImageAddress, String description, float radius, float areaDamageOrDuration) {
        super(name, cost, level, inDeck, cardImageAddress);
        this.description = description;
        this.radius = radius;
        this.areaDamageOrDuration = areaDamageOrDuration;
    }

    private String description;
    private float radius;
    private float areaDamageOrDuration;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getAreaDamage() {
        return areaDamageOrDuration;
    }

    public void setAreaDamage(float areaDamage) {
        this.areaDamageOrDuration = areaDamage;
    }
}
