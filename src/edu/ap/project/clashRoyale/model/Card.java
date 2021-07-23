package edu.ap.project.clashRoyale.model;

import java.io.Serializable;
import java.util.Arrays;

public class Card implements Serializable, Cloneable {
    private String name;
    private String[] forces;
    private int cost;
    private int deckLocation;

    /**
     * Card Constructor
     * @param name name of card
     * @param cost cost per elixir
     * @param deckLocation position in deck
     */
    public Card(String name, int cost, int deckLocation) {
        this.name = name;
        this.cost = cost;
        this.deckLocation = deckLocation;
    }

    /**
     * clone
     * @return card
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException{
        Card card = new Card(name, cost, deckLocation);
        card.setForces(Arrays.copyOf(forces, forces.length));
        return card;
    }

    /**
     * Constructor
     * @param name name of card
     * @param cost cost
     */
    public Card(String name, int cost) {
        this(name, cost, -1);
    }

    /**
     * get name of card
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get cost of card
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * is card in deck
     * @return is card in deck
     */
    public boolean isInDeck() {
        return deckLocation >= 0;
    }

    /**
     * set deck location
     * @param deckLocation
     */
    public void setDeckLocation(int deckLocation) {
        this.deckLocation = deckLocation;
    }

    /**
     * get deck location
     * @return deck location
     */
    public int getDeckLocation() {
        return deckLocation;
    }

    /**
     * set forces
     * @param forces Array of force names
     */
    public void setForces(String[] forces) {
        this.forces = forces;
    }

    /**
     * getter forces
     * @return array of force names
     */
    public String[] getForces() {
        return forces;
    }

    /**
     * get name of index force
     * @param index index of force
     * @return force name
     */
    public String getForce(int index) {
        if(index < forces.length)
            return forces[index];
        return null;
    }
}
