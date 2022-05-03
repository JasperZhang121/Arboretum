package comp1110.ass2.gui;

import java.util.Arrays;
import java.util.List;

public class Cards {
    String species;
    String value;

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
        return Arrays.asList("Cassia","Blue_Spruce","Cherry_Blossom","Dogwood","Jacaranda","Maple");
    }

    @Override
    public String toString() {
        return "Cards{" +
                "species='" + species + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
