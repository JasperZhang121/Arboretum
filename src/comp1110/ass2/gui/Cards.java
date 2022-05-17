package comp1110.ass2.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 *  Create cards with species and value (Constructor, setSpecies, setValue)
 *  Only allow valid species and values (getValidSpecies and getValidValue method), else throw illegalArgumentException
 *  Create get method for species and values
 *  Create getCardImage for getting the Image of the card
 *  Create the toString Method and make it corresponding to the gameState, so each card will be called "a1","a2" etc.
 *  Use main method for testing above things
 */

public class Cards implements Serializable {
    private String species;
    private String value;

    public Cards(String species, String value) {
        setSpecies(species);
        setValue(value);
    }

    public String getSpecies() {
        return species;
    }

    // valid species: Cassia,Blue_Spruce,Cherry_Blossom,Dogwood,Jacaranda,Maple
    // Only run the valid objects, stop when something wrong.
    public void setSpecies(String species) {
        var validSpecies = getValidSpecies();
        if (validSpecies.contains(species)){
            this.species = species;
        } else {
            throw new IllegalArgumentException("valid species are"+getValidSpecies());
        }
    }

    public String getValue() {
        return value;
    }

    // valid values: 1,2,3,4,5,6,7,8.
    // Only run the valid objects, stop when something wrong.
    public void setValue(String value) {
        var validValues = getValidValues();
        if (validValues.contains(value)){
            this.value = value;
        } else {
            throw new IllegalArgumentException("valid values are"+ validValues);
        }
    }

    // method returns valid values for cards
    public static List<String> getValidValues(){
        return Arrays.asList("1","2","3","4","5","6","7","8");
    }

    // method returns valid species for cards
    public static List<String> getValidSpecies(){
        return Arrays.asList("a","b","c","d","j","m");
    }

    @Override
    public String toString() {
        return species + value;
    }

    // get the image of the card
    public ImageView getCardImage(){
        String url = "images/"+species+value+".png";
        ImageView iv = new ImageView(this.getClass().getResource(url).toExternalForm());
        iv.setFitHeight(70);
        iv.setFitWidth(50);
        return iv;
    }
    public Image getImage() throws URISyntaxException {

        String url = "images/"+species+value+".png";
        Image image = new Image(getClass().getResource(url).toURI().toString());
        return image;
    }
    // test
    public static void main(String[] args) {
        var card = new Cards("a","1");
        System.out.println(card);
    }
}
