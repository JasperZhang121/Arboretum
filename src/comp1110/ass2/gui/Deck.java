package comp1110.ass2.gui;

import comp1110.ass2.Arboretum;
import javafx.scene.image.ImageView;
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
 * Create getBackOfCard method for getting the image for the back of card
 * Use main method for testing above things
 * Author: Hao Zhang
 */

public class Deck {

    // Initialize the deck with 48 cards inside
    public ArrayList<Cards> deck = new ArrayList<>(48);
    public int length = 48;

    // put all cards into the deck by using 2 for-loop (species * value)
    public Deck() {
        List<String> species =Cards.getValidSpecies();
        List<String> values = Cards.getValidValues();
        int i =0;
        for(String specie:species){
            for (var value: values){
                deck.add(i++, new Cards(specie,value));
            }
        }
    }

    // Initialize the draw as null
    // Random choose the index of deck, set the deck[index] as the draw card
    // Make the draw card position in deck as null, then return the draw
    public Cards drawCardFromDeck(){
        if (length == 0) return null;
        int index = (int)(Math.random()*length--);
        Cards card = deck.get(index);
        deck.remove(index);
        return card;
    }

    // Using for loop to take the object one by one from the Cards array
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(deck.get(i));
        }
        return stringBuilder.toString();
    }

    // Back of deck
    public ImageView getBackOfCard(){
        String url = "images/Back.png";
        ImageView iv = new ImageView(this.getClass().getResource(url).toExternalForm());
        iv.setFitHeight(70);
        iv.setFitWidth(50);
        return iv;
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
