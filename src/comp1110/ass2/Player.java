package comp1110.ass2;

public class Player {
    static String name;
    static Card [] cards;

    public Player(String name, Card[] cards) {
        this.name = name;
        this.cards = cards;
    }

    public static String getName() {
        return name;
    }

    public static Card[] getCards() {
        return cards;
    }
}
