package edu.AP.Project.ClashRoyale.Client.State;

import edu.AP.Project.ClashRoyale.Client.Models.Card;

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
