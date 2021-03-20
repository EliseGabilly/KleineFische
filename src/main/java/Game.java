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

        Card card = null;
        while (pile.hasCard() && !drawDuplicate && !drawOctopus) {
            card = pile.drawOne();

            drawOctopus = card.getType().equals(Card.FishType.o);
            drawDuplicate = draw.isCardDuplicate(card);

            draw.addCard(card);
            draw.printCards();
        }

        if(drawDuplicate){
            int ind = draw.indexOfDuplicate(card);
            player.takeCards(draw.getCardsList().subList(0,ind));
            discard.addCards(draw.getCardsList().subList(ind, draw.getCardsList().size()));
        }else if(drawOctopus){
            discard.addCards(draw);
        } else{
            player.takeCards(draw);
        }

    }


}
