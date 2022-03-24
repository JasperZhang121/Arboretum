package comp1110.ass2;

public class Players {
    String name;
    int [] cards;

    public Players(String name, int[] cards) {
        this.name = name;
        this.cards = cards;
    }



    public String getName() {
        return name;
    }

    public int[] getCards() {
        return cards;
    }
}
