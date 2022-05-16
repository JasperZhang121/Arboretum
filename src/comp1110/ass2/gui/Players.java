package comp1110.ass2.gui;

import comp1110.ass2.Player;

import java.util.Arrays;
import java.util.List;

/**
 *  Create the player class, and the input of constructor is hand (cards array) and name (String)
 *  Create method of getCard and put Card
 *  Create the toString method to make it corresponding the gameState, so it will be called like "Aa1b1c1", "Bc2","B" etc.
 *  Use main method for testing above things
 */

public class Players {

    private Cards[] hand;
    private String name;

    public Players(Cards[] hand, String name) {
        this.hand = hand;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // get the card from deck and give it to the hand
    // if the hand is null, create a new object array and put the card as the first card in it, then assign the new array to the old
    // else, detect the first null value, if exist then assign the card to that position and break
    public void getCard (Cards card){
        if (hand==null){
            var hand1 = new Cards[9];
            hand1[0]=card;
            hand=hand1;
        } else {
            for (int i = 1; i < hand.length; i++) {
                if (hand[i]==null){
                    hand[i] = card;
                    break;
                }
            }
        }
    }

    // put the card to other places (discard or placement)
    // if there are cards on hand, set the int card to null as it will be removed
    public void putCard (Cards card){
        if (hand!=null){
            for (int i = 0; i < hand.length; i++) {
                if (hand[i]==card){
                    hand[i]=null;
                }
            }
        }
    }

    // If no cards,then return empty string
    // else using for-loop to take the object one by one from the Cards array
    @Override
    public String toString() {
        if (hand==null)return name;
        StringBuilder stringBuilder =new StringBuilder();
        for (int i = 0; i < hand.length; i++) {
            if (hand[i]!=null){
                stringBuilder.append(hand[i]);
            } else {
                stringBuilder.append("");
            }
        }
        return   name + stringBuilder;
    }

    // test getCard and putCard method
    // test toString method
    public static void main(String[] args) {
        var testCard1 = new Cards("a","1");
        var testCard2 = new Cards("b","1");
        var testCard3 = new Cards("c","1");
        var play = new Players(null,"A");
        System.out.println(play);

        play.getCard(testCard1);
        System.out.println(play);

        play.getCard(testCard2);
        play.getCard(testCard3);
        System.out.println(play);

        play.putCard(testCard1);
        play.putCard(testCard2);
        System.out.println(play);
        play.putCard(testCard3);
        System.out.println(play);

        play.getCard(testCard1);
        play.getCard(testCard2);
        play.getCard(testCard3);
        System.out.println(play);



    }
}
