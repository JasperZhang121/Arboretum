package comp1110.ass2;

public class Card {
    enum Species{
        blue_spruce,
        Cassia,
        Cherry_Blossom,
        Dogwood,
        Jacaranda,
        Maple,
        Oak,
        Royal_Poinciana,
        Tulip_Poplar,
        Willow;
    }
    int value;
    Species species;
    Card(Species species, int value){
        this.species = species;
        if (value > 8){
            this.value = 8;
        }
        if (value < 1){
            this.value = 1;
        }
        else{
            this.value = value;
        }
    }
}
