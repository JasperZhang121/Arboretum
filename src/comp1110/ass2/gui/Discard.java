package comp1110.ass2.gui;

import javafx.scene.image.ImageView;

import java.util.Arrays;

/** Create the Discard (set the Maximum cards inside as 9)
 *  Allow discard cards
 *  Allow draw top card (not random but top card)
 *  Create toString method to make the Discard corresponding to the gameState
 *  Create the getBackOfCard method for getting the back image of card
 *  Use main method for testing above things
 */

public class Discard {
    private Cards [] discard;
    private String name;

    public Discard(Cards [] discard, String name) {
        this.discard = discard;
        this.name = name;
    }


    // Allow discard in the discard pile
    // if the discard is null, create a new object array and put the first card inside, then assign it to the discard
    // detect the first null position in the object array, then replace it, and break
    public void discardCard(Cards card){
        if (discard==null){
                Cards [] discard1 = new Cards[9];
                discard1[0]=card;
                discard=discard1;
        } else {
            for (int i = 0; i < discard.length; i++) {
                if (discard[i]==null){
                    discard[i]=card;
                    break;
                }
            }
        }
    }

    // Allow draw from the discard pile
    // Else, detect the null position in object array, return the card before the null, set the return card position to null
    public Cards drawFromDiscard(){
        Cards returnCard;
        if (discard != null) {
            for (int i = 0; i < discard.length; i++) {
                if (discard[i] == null) {
                    returnCard = discard[i-1];
                    discard[i-1]=null;
                    return returnCard;
                }
            }
        }
        return null;
    }
    // Back of discard
    public ImageView getBackOfCard(){
        String url = "images/Back.png";
        ImageView iv = new ImageView(this.getClass().getResource(url).toExternalForm());
        iv.setFitHeight(70);
        iv.setFitWidth(50);
        return iv;
    }

    // Using for loop to take the object one by one from the Cards array
    @Override
    public String toString() {
        if (discard==null)return name;
        StringBuilder stringBuilder =new StringBuilder();
        for (int i = 0; i < discard.length; i++) {
            if (discard[i]!=null){
                stringBuilder.append(discard[i]);
            } else {
                stringBuilder.append("");
            }
        }
        return   name + stringBuilder;
    }

    //test draw and discard
    public static void main(String[] args) {

        var testCard1 = new Cards("a","1");
        var testCard2 = new Cards("b","1");
        var testCard3 = new Cards("c","1");

        var dis = new Discard(null,"A");
        System.out.println(dis);

        dis.discardCard(testCard1);
        System.out.println(dis);

        dis.discardCard(testCard2);
        System.out.println(dis);

        dis.discardCard(testCard3);
        System.out.println(dis);


        dis.drawFromDiscard();
        System.out.println(dis);

        dis.drawFromDiscard();
        System.out.println(dis);

        dis.drawFromDiscard();
        System.out.println(dis);
    }
}



