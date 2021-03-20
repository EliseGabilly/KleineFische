package main.java;

public class Player {

    private String name;
    private int numberID;
    private Deck hand = new Deck(false);;

    Player (int  numberID, String name){
        this.numberID = numberID;
        this.name = name;
    }

    public void takeCards(Deck cards){
        hand.addCards(cards);
    }

}
