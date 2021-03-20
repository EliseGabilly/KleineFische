package main.java;

public class Game {

    Player[] playerList;
    int roundNb = 0;
    Deck pile = new Deck(true); //pioche
    Deck discard = new Deck(false); //défausse
    Deck draw = new Deck(false);

    Game (Player[] playerList) throws Exception {
        if(playerList.length >4 || playerList.length < 2){
            throw new Exception("This game is for 2 to 4 players. (actual number of players :"+playerList.length+")");
        } else {
            this.playerList = playerList;
        }
        System.out.println("game start");
        pile.printCards();
        discard.printCards();
        draw.printCards();
    }

}
