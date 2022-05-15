package comp1110.ass2.gui;

import comp1110.ass2.Player;

import java.util.List;

public class Players {

    private Cards[] hand;
    private String name;

    public Players(Cards[] hand, String name) {
        this.hand = hand;
        this.name = name;
    }

    // get the card from deck and give it to the hand
    // if the hand is null, create a new object array and put the card as the first card in it, then assign the new array to the old
    // detect the first null value, if exist then assign the card to that position

    public void getCard (Cards card){

        if (hand==null){
            var hand1 = new Cards[9];
            hand1[0]=card;
            hand=hand1;
        }
        for (int i = 0; i < hand.length; i++) {
             if (hand[i]==null){
                 hand[i] = card;
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

    // test
    public static void main(String[] args) {
        var testCard1 = new Cards("a","1");
        var testCard2 = new Cards("b","1");
        var testCard3 = new Cards("c","1");
        var play = new Players(null,"A");
        play.getCard(testCard1);
        play.getCard(testCard2);
        play.getCard(testCard3);
        System.out.println(play);
    }
}
