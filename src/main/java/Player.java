package main.java;

public class Player {

    String name;
    int numberID;
    Deck hand = new Deck(false);;

    Player (int  numberID, String name){
        this.numberID = numberID;
        this.name = name;
    }

}
