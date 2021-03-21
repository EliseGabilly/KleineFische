package main.java;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private final Player[] playerList;
    private int roundNb = 0;
    private final Deck pile = new Deck(true); //pioche
    private final Deck discard = new Deck(false); //défausse

    Scanner in = new Scanner(System.in);

    Game (Player[] playerList) throws Exception {
        if(playerList.length >4 || playerList.length < 2){
            throw new Exception("This game is for 2 to 4 players. (actual number of players :"+playerList.length+")");
        } else {
            Collections.shuffle(Arrays.asList(playerList));
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
//                String s = in.nextLine();
                String s = "y";
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
            System.out.println("octopus");
            octopusEnd(player);
        } else{
            player.takeCards(draw);
            System.out.println("safe turn");
        }
    }

    private void octopusEnd(Player player){
        //select opponent player
        Optional<Player> opponentChoosed = selectOpponent(player);
        if(opponentChoosed.isPresent()){
            System.out.println("You bet against : "+opponentChoosed.get().getName());
            int bet = placeBet(Math.min(opponentChoosed.get().getHand().getCardsList().size(), 3));
            int dice = roleDice();
            System.out.println(dice);
            System.out.println(bet);

        }
    }

    private Optional<Player> selectOpponent(Player player) {
        Optional<Player> chosenOne = Optional.empty();
        List<Player> opponents = Arrays.asList(playerList);
        opponents = opponents.stream().filter(p -> p.getNumberID()!=player.getNumberID() && p.getHand().hasCard())
                .collect(Collectors.toList());
        if(opponents.isEmpty()){
            System.out.println("Sorry noone else have cards to steel from");
        } else if(opponents.size()==1){
            chosenOne = Optional.of(opponents.get(0));
        } else {
            System.out.print("You can choose from : ");
            opponents.forEach(p -> System.out.println(p.getName() + " (id: "+p.getNumberID()+") have "+p.getHand().getCardsList().size()));
            do{
                System.out.print("Input player ID :");
                try {
                    int chosenID = Integer.parseInt(in.nextLine());
                    if(opponents.stream().anyMatch(p -> p.getNumberID()== chosenID)){
                        chosenOne = opponents.stream().filter(p -> p.getNumberID()== chosenID)
                                .findFirst();
                    } else {
                        System.out.println("Invalid ID");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Invalid ID");
                }
            } while (chosenOne.isEmpty());
        }
        return chosenOne;
    }

    private int roleDice() {
        List<Integer> givenList = Arrays.asList(1, 2, 3);
        Random rand = new Random();
        return givenList.get(rand.nextInt(givenList.size()));
    }

    private int placeBet(int highestBet) {
        boolean validEntry = false;
        int bet = 0;

        do{
            System.out.print("Wanna bet ? (1 to "+highestBet+") ");
            try {
                bet = Integer.parseInt(in.nextLine());
                if(bet<=highestBet && bet>=1){
                    validEntry = true;
                } else {
                    System.out.println("Please bet (1 to "+highestBet+") ");
                }
            } catch (NumberFormatException e){
                System.out.println("Please bet (1 to "+highestBet+") ");
            }
        } while (!validEntry);
        return bet;
    }

}
