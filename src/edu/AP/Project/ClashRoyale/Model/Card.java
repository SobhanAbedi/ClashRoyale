package edu.AP.Project.ClashRoyale.Model;

import java.io.Serializable;
import java.util.Arrays;

public class Card implements Serializable, Cloneable {
    private String name;
    private String[] forces;
    private int cost;
    private int deckLocation;

    public Card(String name, int cost, int deckLocation) {
        this.name = name;
        this.cost = cost;
        this.deckLocation = deckLocation;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Card card = new Card(name, cost, deckLocation);
        card.setForces(Arrays.copyOf(forces, forces.length));
        return card;
    }

    public Card(String name, int cost) {
        this(name, cost, -1);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public boolean isInDeck() {
        return deckLocation > 0;
    }

    public void setDeckLocation(int deckLocation) {
        this.deckLocation = deckLocation;
    }

    public int getDeckLocation() {
        return deckLocation;
    }

    public void setForces(String[] forces) {
        this.forces = forces;
    }

    public String[] getForces() {
        return forces;
    }

    public String getForce(int index) {
        if(index < forces.length)
            return forces[index];
        return null;
    }
}
