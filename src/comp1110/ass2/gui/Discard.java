package comp1110.ass2.gui;

/** Create the Discard (set the Maximum cards inside as 9)
 *  Allow discard cards
 *  Allow draw top card (not random but top card)
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
        }

        for (int i = 0; i < discard.length; i++) {
             if (discard[i]==null){
                 discard[i]=card;
                 break;
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


    //test draw and discard
    public static void main(String[] args) {

        var testCard1 = new Cards("a","1");
        var testCard2 = new Cards("b","1");
        var testCard3 = new Cards("c","1");

        var dis = new Discard(null,"A");
        dis.discardCard(testCard1);
        dis.discardCard(testCard2);
        dis.discardCard(testCard3);
        System.out.println(dis.drawFromDiscard());


    }
}



