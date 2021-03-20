package main.java;

public class Card {

    enum FishType {zero, one, two, three, four, five, six, seven, height, nine, ten};
    FishType type;
    int value; //0 if octopus

    Card(FishType type, int value){
        this.type = type;
        this.value = value;
    }

    public FishType getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
}
