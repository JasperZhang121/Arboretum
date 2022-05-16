package comp1110.ass2.gui;

import comp1110.ass2.Arboretum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create the deck (48 cards).
 * Adding drawFromDeck method to get cards in the deck, and no action when the deck is empty
 * Create the toString to make the deck corresponding to the gameState
 * Use main method for testing above things
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
    public Cards drawCardFromDeck(){
        Cards draw = null;
        while (draw==null){
            int index = (int)(Math.random()*deck.length);
            draw = deck[index];
            deck[index]=null;
        }
        return draw;
    }

    // Using for loop to take the object one by one from the Cards array
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < deck.length; i++) {
            stringBuilder.append(deck[i]);
        }
        return stringBuilder.toString();
    }

    //test whether the deck can draw the card until the deck is empty
    //test the toString method
    public static void main(String[] args) {
        var testDeck = new Deck();
        int count=0;
        for (int i = 0; i < 100; i++) {
            System.out.println(testDeck.drawCardFromDeck());
            count++;
            System.out.println(count);
        }
        System.out.println(testDeck);
    }
}
