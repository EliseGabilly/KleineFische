package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import main.java.Card.FishType;

public class Deck {

    private List<Card> cardsList = new ArrayList<>();

    Deck (boolean isPile){
        if (isPile){
            int[] cardValues = new int[]{1, 1, 2, 2, 3, 4};
            Arrays.stream(FishType.values()).skip(1)
                                            .forEach(fishType -> {
                                                Arrays.stream(cardValues).forEach(val -> {
                                                    Card aCard = new Card(fishType, val);
                                                    cardsList.add(aCard);
                                                });
                                            });
            Card octopus = new Card(FishType.o, 0);
            cardsList.addAll(Collections.nCopies(6, octopus));
            Collections.shuffle(cardsList);
        }
    }

    public void printCards(){
        cardsList.stream().forEach(card -> {
            System.out.print(card.getType()+" "+card.getValue()+"; ");
        });
        System.out.println("");
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

    public boolean isCardDuplicate (Card cardDrawn){
        return cardsList.stream().anyMatch(card -> card.getType().equals(cardDrawn.getType()));
    }

    public List<Card> getCardsList(){
        return cardsList;
    }
}
