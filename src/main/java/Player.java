package main.java;

public class Player {

    String name;
    int numberID;
    Deck hand;

    Player (int  numberID){
        this.numberID = numberID;
        this.hand = new Deck(false);

    }

}
