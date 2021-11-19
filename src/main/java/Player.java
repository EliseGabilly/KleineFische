package main.java;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final int numberID;
    private final Deck hand = new Deck(false);

    Player (int  numberID, String name){
        this.numberID = numberID;
        this.name = name;
    }

    public void takeCards(Deck cards){
        hand.addCards(cards);
    }
    public void takeCards(List<Card> cards){
        hand.addCards(cards);
    }

    public List<Card> giveRdmCard(int nbOfCardToGive){
        List<Card> l = new ArrayList<>();
        hand.shuffle();
        for(int i =0; i<nbOfCardToGive; i++){
            l.add(hand.drawOne());
        }
        return l;
    }

    public String getName() {
        return this.name;
    }

    public int getNumberID() {
        return numberID;
    }

    public Deck getHand() {
        return hand;
    }
}
