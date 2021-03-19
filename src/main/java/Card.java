package main.java;

public class Card {

    enum FishType {};
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
