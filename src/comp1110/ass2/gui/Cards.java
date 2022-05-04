package comp1110.ass2.gui;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Cards {
    private String species;
    private String value;
    private Image image;

    public Cards(String species, String value) {
        setSpecies(species);
        setValue(value);
        //String file = species.charAt(0)+value+".png";
        //image = new Image("comp1110/ass2/gui/images/"+file);
    }

    public String getSpecies() {
        return species;
    }

    // valid species Cassia,Blue_Spruce,Cherry_Blossom,Dogwood,Jacaranda,Maple
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

    // valid values 1,2,3,4,5,6,7,8.
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
        return Arrays.asList("a_Cassia","b_Blue_Spruce","c_Cherry_Blossom","d_Dogwood","j_Jacaranda","m_Maple");
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "species='" + species + '\'' +
                ", value='" + value + '\'' +
                '}';
    }


   /* //test
    public static void main(String[] args) {
        String species= "abcdefj";
        String value = "1";
        String file = species.charAt(0)+value+".png";
        var card1 = new Cards("a_Cassia","1");
        System.out.println(file);
        System.out.println(card1);
    }*/
}
