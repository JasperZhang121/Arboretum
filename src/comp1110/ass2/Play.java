package comp1110.ass2;

public class Play {
    static String turn;
    static String deck;
    static String handA;
    static String handB;
    static String arboretumA;
    static String arboretumB;
    static String discardA;
    static String discardB;

    public Play(String[][] gameState) {
        this.turn = gameState[0][0];
        this.arboretumA = gameState[0][1];
        this.handA = gameState[0][2];
        this.arboretumB = gameState[0][3];
        this.handB = gameState[0][4];
        this.deck = gameState[1][0];
        this.discardA = gameState[1][1];
        this.discardB = gameState[1][2];
    }

    public void DrawSevenCards(){

    }

    public void RemoveTopSevenCards(){

    }

    public void DrawTwo(){

    }

    public void PlayOne(){

    }

    public void Discard() {

    }

    public String Turn() {
        return turn;
    }

   public int Comparing (int [] TwoCardsOfPlayerA, int [] TwoCardsOfPlayerB){

        return 1;// higher score
   }

    public int Scoring(int WinnerInComparingCard){

        return 1;// score;
    }

}
