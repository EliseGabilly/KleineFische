package main.java;

public class Game {

    private Player[] playerList;
    private int roundNb = 0;
    private final Deck pile = new Deck(true); //pioche
    private final Deck discard = new Deck(false); //défausse

    Game (Player[] playerList) throws Exception {
        if(playerList.length >4 || playerList.length < 2){
            throw new Exception("This game is for 2 to 4 players. (actual number of players :"+playerList.length+")");
        } else {
            this.playerList = playerList;
        }
    }

    public void start(){
        while(pile.hasCard()){
            roundNb+=1;
            for(Player player : playerList){
                turn(player);
            }
        }
    }

    private void turn(Player player) {
        Deck draw = new Deck(false);
        boolean drawOctopus = false;
        boolean drawDuplicate = false;

        while (pile.hasCard() && !drawDuplicate && !drawOctopus) {
            Card card = pile.drawOne();
            if (card.getType().equals(Card.FishType.o)){
                drawOctopus = true;
                //TODO roll dice
            } else if (draw.isCardDuplicate(card)){
                drawDuplicate = true;
                //TODO end turn
            }
            draw.addCard(card);
            draw.printCards();
        }

        if(drawDuplicate){

        }else if(drawOctopus){
            discard.addCards(draw);
        } else{
            player.takeCards(draw);
        }


    }


}
