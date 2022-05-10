package comp1110.ass2.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    // get the image of the card
    public ImageView getCardImage(){
        String url = "images/"+species.charAt(0)+value+".png";
        ImageView iv = new ImageView(this.getClass().getResource(url).toExternalForm());
        iv.setFitWidth(70);
        iv.setY(50);
        return iv;
    }


}
