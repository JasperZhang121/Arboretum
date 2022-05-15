package comp1110.ass2.gui;

import java.util.List;

public class Players {

    private List<Cards> hand;
    private String name;

    public Players(List<Cards> hand, String name) {
        this.hand = hand;
        this.name = name;
    }

    // get the card from deck and give it to the hand
    public void getCard (Cards card){
         if (hand.size()<9 && hand.size()>=7){
             hand.add(card);
         }
    }

    // put the card to other places (discard or placement)
    public void putCard (Cards card){
        if (hand.size()<=9 && hand.size()>7){
            hand.remove(card);
        }
    }

}
