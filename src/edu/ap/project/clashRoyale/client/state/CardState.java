package edu.ap.project.clashRoyale.client.state;

import edu.ap.project.clashRoyale.client.models.Card;

import java.util.ArrayList;

public class CardState {
    private static ArrayList<Card> cards ;


    public CardState(){
        cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void addCard(Card card){
        cards.add(card);
    }


}
