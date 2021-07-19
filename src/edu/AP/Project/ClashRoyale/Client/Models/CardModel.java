package edu.AP.Project.ClashRoyale.Client.Models;

public class CardModel {
    private boolean inDeck;
    private int level;
    private String cardImageAddress;

    public CardModel(boolean inDeck, int level, String cardImageAddress) {
        this.inDeck = inDeck;
        this.level = level;
        this.cardImageAddress = cardImageAddress;
    }

    public boolean isInDeck() {
        return inDeck;
    }

    public void setInDeck(boolean inDeck) {
        this.inDeck = inDeck;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCardImageAddress() {
        return cardImageAddress;
    }

    public void setCardImageAddress(String cardImageAddress) {
        this.cardImageAddress = cardImageAddress;
    }
}
