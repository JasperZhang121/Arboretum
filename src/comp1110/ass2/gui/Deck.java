package comp1110.ass2.gui;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private ArrayList<Cards>deck;

    // collection of cards
    public Deck(ArrayList<Cards> deck) {
        this.deck = deck;
    }
    public Deck() {
        List<String> species =Cards.getValidSpecies();
        List<String> values = Cards.getValidValues();
        deck = new ArrayList<>();
        for(var specie:species){
            for (var value: values){
                deck.add(new Cards(specie,value));
            }
        }
    }

    public static void main(String[] args) {
        var deck = new Deck();

    }
}
