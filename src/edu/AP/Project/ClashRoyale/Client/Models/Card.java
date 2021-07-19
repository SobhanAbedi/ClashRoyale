package edu.AP.Project.ClashRoyale.Client.Models;

import javafx.scene.image.Image;

import java.util.Objects;

public class Card {


    protected int cost ;
    protected Image cardImage;
    protected int level = 1;
    protected boolean inDeck;
    protected String name;

    public Card(String name, int cost, int level, boolean inDeck, String cardImageAddress) {
        this.name = name;
        this.cost = cost;
        this.cardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(cardImageAddress)));
        this.level = level;
        this.inDeck = inDeck;
    }

    public boolean isInDeck() {
        return inDeck;
    }

    public void setInDeck(boolean inDeck) {
        this.inDeck = inDeck;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Image getCardImage() {
        return cardImage;
    }

    public void setCardImage(Image cardImage) {
        this.cardImage = cardImage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
