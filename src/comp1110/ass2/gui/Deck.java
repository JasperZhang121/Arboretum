package comp1110.ass2.gui;

import comp1110.ass2.Arboretum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create the deck (48 cards).
 * Adding drawCard method to get cards in the deck until deck is empty
 */

public class Deck {

    // Initialize the deck with 48 cards inside
    private Cards[]deck = new Cards[48];

    // put all cards into the deck by using 2 for-loop (species * value)
    public Deck() {
        List<String> species =Cards.getValidSpecies();
        List<String> values = Cards.getValidValues();
        int i =0;
        for(var specie:species){
            for (var value: values){
                deck [i++] = new Cards(specie,value);
            }
        }
    }

    // Initialize the draw as null
    // Random choose the index of deck, set the deck[index] as the draw card
    // Make the draw card position in deck as null, then return the draw
    public Cards drawCard(){
        Cards draw = null;
        while (draw==null){
            int index = (int)(Math.random()*deck.length);
            draw = deck[index];
            deck[index]=null;
        }
        return draw;
    }

    //test whether the deck can draw the card until the deck is empty
    public static void main(String[] args) {
        var testDeck = new Deck();
        int count=0;
        for (int i = 0; i < 100; i++) {
            System.out.println(testDeck.drawCard());
            count++;
            System.out.println(count);
        }
    }
}
