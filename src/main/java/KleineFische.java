package main.java;

import java.util.Arrays;

public class KleineFische {

    public static void main(String[] args) {
        Player p1 = new Player(1, "Alice");
        Player p2 = new Player(2, "Bob");

        Game aGame = null;
        try {
            aGame = new Game(new Player[]{p1, p2});
        } catch (Exception e) {
            e.printStackTrace();
        }
        aGame.start();
    }
}
