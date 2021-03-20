package main.java;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import main.java.Card.FishType;

public class Deck {

    private final List<Card> cardsList = new ArrayList<>();

    Deck (boolean isPile){
        if (isPile){
            int[] cardValues = new int[]{1, 1, 2, 2, 3, 4};
            Arrays.stream(FishType.values()).skip(1)
                                            .forEach(fishType -> Arrays.stream(cardValues).mapToObj(val -> new Card(fishType, val)).forEach(cardsList::add));
            Card octopus = new Card(FishType.o, 0);
            cardsList.addAll(Collections.nCopies(6, octopus));
            Collections.shuffle(cardsList);
        }
    }

    public void printCards(){
        for (Card card : cardsList) {
            System.out.print(card.getType() + " " + card.getValue() + "; ");
        }
        System.out.println();
    }

    public boolean hasCard() {
        return !cardsList.isEmpty();
    }

    public Card drawOne(){
        return cardsList.remove(0);
    }

    public void addCard(Card card){
        cardsList.add(card);
    }

    public void addCards(Deck cards){
        cardsList.addAll(cards.getCardsList());
    }
    public void addCards(List<Card> cards){
        cardsList.addAll(cards);
    }

    public boolean isCardDuplicate (Card cardDrawn){
        return cardsList.stream().anyMatch(card -> card.getType().equals(cardDrawn.getType()));
    }

    public int indexOfDuplicate (Card cardDrawn){
        int index = 0;
        for(Card card : cardsList){
            if(card.getType().equals(cardDrawn.getType())){
                return index;
            }
            index++;
        }
        return index;
    }

    public List<Card> getCardsList(){
        return cardsList;
    }

    public int countPoints(){
        Map<FishType, Integer> bestCards = new EnumMap<>(FishType.class);
        for (Card card: cardsList){
            if (bestCards.get(card.getType()) == null || bestCards.get(card.getType()) < card.getValue()){
                bestCards.put(card.getType(), card.getValue());
            }
        }
        return bestCards.values().stream().reduce(0, Integer::sum);
    }
}
