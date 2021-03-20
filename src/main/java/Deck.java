package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import main.java.Card.FishType;

public class Deck {

    List<Card> cardsList = new ArrayList<>();

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
            System.out.print(card.type+" "+card.value+"; ");
        });
    }

}
