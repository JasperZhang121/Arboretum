package comp1110.ass2;

public class Players {
    String name;
    Card [] cards;

    public Players(String name, Card[] cards) {
        this.name = name;
        this.cards = cards;
    }



    public String getName() {
        return name;
    }

    public Card[] getCards() {
        return cards;
    }
}
