package main.java;

import java.util.Scanner;

public class Game {

    private final Player[] playerList;
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
        showResult();
    }

    private void showResult() {
        System.out.println("The result --- ");
        System.out.println("After "+roundNb+" rounds");
        for(Player player : playerList){
            System.out.println(player.getName()+" : ");
            System.out.print("Final hand : ");
            player.getHand().printCards();
            System.out.println("Score : "+player.getHand().countPoints());
        }
    }

    Scanner in = new Scanner(System.in);
    private void turn(Player player) {
        System.out.println(player.getName() + " : ");
        Deck draw = new Deck(false);
        boolean playerContinue = true;

        boolean drawOctopus = false;
        boolean drawDuplicate = false;
        boolean canContinue;

        Card card = null;
        while (playerContinue && pile.hasCard()) {
            card = pile.drawOne();

            drawOctopus = card.getType().equals(Card.FishType.o);
            drawDuplicate = draw.isCardDuplicate(card);
            draw.addCard(card);
            draw.printCards();

            canContinue = !drawDuplicate && !drawOctopus;
            playerContinue = isPlayerContinuing(canContinue);
        }

        endOfTurn(player, draw, drawOctopus, drawDuplicate, card);
    }

    private boolean isPlayerContinuing(boolean canContinue) {
        boolean playerContinue = true;
        if(canContinue){
            boolean validEntry = false;
            do{
                System.out.print("Wanna play again ? (y/n) ");
                String s = in.nextLine();
                if(s.equals("y") || s.equals("Y")){
                    validEntry = true;
                } else if (s.equals("n") || s.equals("N")){
                    playerContinue = false;
                    validEntry = true;
                }
            } while (!validEntry);

        } else {
            playerContinue = false;
        }
        return playerContinue;
    }

    private void endOfTurn(Player player, Deck draw, boolean drawOctopus, boolean drawDuplicate, Card card) {
        System.out.print("End of turn : ");
        if(drawDuplicate){
            int ind = draw.indexOfDuplicate(card);
            player.takeCards(draw.getCardsList().subList(0,ind));
            discard.addCards(draw.getCardsList().subList(ind, draw.getCardsList().size()));
            System.out.println("duplicate");
        }else if(drawOctopus){
            discard.addCards(draw);
            //TODO
            System.out.println("octopus");
        } else{
            player.takeCards(draw);
            System.out.println("safe turn");
        }
    }

}
