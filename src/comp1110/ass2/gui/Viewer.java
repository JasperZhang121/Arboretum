package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;
    private static final int GRID_SIZE = 50;
    private static final int GRID_DIMENSION = 10;
    private static final int WINDOW_XOFFSET = 10;
    private static final int WINDOW_YOFFSET = 30;
    private static final int TEXTBOX_WIDTH = 120;

    private final Group root = new Group();
    private final Group controls = new Group();

    private TextField turnIDTextField;
    private TextField aArboretumTextField;
    private TextField bArboretumTextField;
    private TextField aDiscardTextField;
    private TextField bDiscardTextField;
    private TextField deckTextField;
    private TextField aHandTextField;
    private TextField bHandTextField;

    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param gameState TASK 6
     */
    void displayState(String[][] gameState) {
        // String [sharedState][hiddenState]
        // Shared : StringID   ArboretumA   DiscardA    ArboretumB    DiscardB
        // Hidden : Deck       HandA        HandB
        root.getChildren().clear();
        var ID = new Text(0,80,"Player Turn Id: " + gameState[0][0]);
        var handA = new Text(0,100,"HandA: " + gameState[1][1]);
        var ArboretumA = new Text(600,50,"ArboretumA: ");
        var ArboretumB = new Text(200,400,"ArboretumB: " );
        var ArboretumA_Center = new Text(800,250,gameState[0][1].substring(1,3));
        var ArboretumB_Center = new Text(500,450,gameState[0][3].substring(1,3));
        var handB = new Text(0,160,"HandB: " + gameState[1][2]);
        var discardA = new Text(0,120,"DiscardA: "+ gameState[0][2]);
        var discardB = new Text(0,180,"DiscardB: "+ gameState[0][4]);
        var deck = new Text(0,140,"deck: "+ gameState[1][0] );
        root.getChildren().addAll(handA,ArboretumA,ArboretumB,handB,discardA,discardB,deck,ArboretumA_Center,ArboretumB_Center,ID);
        int NSC =0;
        int EWC =0;
        int oneSquare = 20;
        cardsPlacement(gameState,1,800,250);
        cardsPlacement(gameState,3,500,450);
        // FIXME Task 6: implement the simple state viewer
    }
    String cardsPlacement(String[][] gameState,int Arboretum,int centerX,int centerY){
        StringBuilder stringBuilder = new StringBuilder();
        int NSC =0;
        int EWC =0;
        int oneSquare = 20;
        String a = gameState[0][Arboretum].substring(1);
        for (int i = 0; i < a.substring(1).length(); i++) {
            if (i!=0 && i%8==0){
                int distanceNS = Integer.parseInt(a.substring(i+3,i+5));
                int distanceEW = Integer.parseInt(a.substring(i+6,i+8));
                if (a.charAt(i+2) == 'N') NSC = distanceNS;
                if (a.charAt(i+2) == 'S') NSC = -1*distanceNS;
                if (a.charAt(i+2) == 'C') NSC = 0;
                if (a.charAt(i+5) == 'E') EWC = distanceEW;
                if (a.charAt(i+5) == 'W') EWC = -1*distanceEW;
                if (a.charAt(i+5) == 'C') EWC = 0;
                stringBuilder.append("("+ EWC+","+NSC+")");
                var cardAB = new Text(centerX+EWC*oneSquare,centerY-NSC*oneSquare,a.substring(i,i+2));
                root.getChildren().addAll(cardAB);
            }
        }
        return stringBuilder.toString();
    }

    @Test
    void cardsPlacementTest (){
        // Arboretum 1 means test A
        // Arboretum 2 means test B
        String [] sharedState = new String[]{"A","Aa1C00C00a2S01C00a3S01E01a4S02E01","Aa5a6a7","Bb1C00C00b2N01C00b3N01W01b4N02W01","Bb5b6b7"};
        String [] hiddenState = new String[]{"j1j2j3j4j5j6j7m1m2m3m4m5m6m7","c1c2c3c4c5c6c7","d1d2d3d4d5d6d7"};
        String [] [] gameState = new String[][]{sharedState,hiddenState};

        // test 7 for a true coordination of Arboretum A
        StringBuilder test7 = new StringBuilder();
        test7.append("(0,-1)(1,-1)(1,-2)");
        boolean a = (cardsPlacement(gameState,1,800,250).equals(test7.toString()));
        Assertions.assertTrue(a);

        // test 8 for a false coordination of Arboretum A
        StringBuilder test8 = new StringBuilder();
        test8.append("(0,-1)(1,-1)(1,-3)");
        boolean b = (cardsPlacement(gameState,1,800,250).equals(test8.toString()));
        Assertions.assertFalse(b);

        // test 9 for a true coordination of Arboretum B
        StringBuilder test9 = new StringBuilder();
        test9.append("(0,1)(-1,1)(-1,2)");
        boolean c = (cardsPlacement(gameState,3,500,450).equals(test9.toString()));
        Assertions.assertTrue(c);

        // test 8 for a false coordination of Arboretum B
        StringBuilder test10 = new StringBuilder();
        test10.append("(0,1)(-1,1)(-1,3)");
        boolean d = (cardsPlacement(gameState,3,500,450).equals(test10.toString()));
        Assertions.assertFalse(d);
    }
    // Test end

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Player Turn ID");
        turnIDTextField = new TextField();
        turnIDTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label aArboretum = new Label("Player A Arboretum:");
        aArboretumTextField = new TextField();
        aArboretumTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label aDiscard = new Label("Player A Discard:");
        aDiscardTextField = new TextField();
        aDiscardTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label bArboretum = new Label("Player B Arboretum:");
        bArboretumTextField = new TextField();
        bArboretumTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label bDiscard = new Label("Player B Discard:");
        bDiscardTextField = new TextField();
        bDiscardTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label deck = new Label("Deck:");
        deckTextField = new TextField();
        deckTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label aHand = new Label("Player A Hand:");
        aHandTextField = new TextField();
        aHandTextField.setPrefWidth(TEXTBOX_WIDTH);
        Label bHand = new Label("Player B Discard:");
        bHandTextField = new TextField();
        bHandTextField = new TextField();
        bHandTextField.setPrefWidth(TEXTBOX_WIDTH);

        Button displayState = new Button("Display State");
        displayState.setOnAction(e -> {
            String[] sharedState = {turnIDTextField.getText(), aArboretumTextField.getText(),
                    aDiscardTextField.getText(), bArboretumTextField.getText(), bDiscardTextField.getText()};
            String[] hiddenState = {deckTextField.getText(), aHandTextField.getText(), bHandTextField.getText()};
            displayState(new String[][]{sharedState, hiddenState});
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(boardLabel, turnIDTextField, aArboretum, aArboretumTextField, aDiscard,
                aDiscardTextField, bArboretum, bArboretumTextField, bDiscard, bDiscardTextField, deck, deckTextField,
                aHand, aHandTextField, bHand, bHandTextField, displayState);
        vbox.setSpacing(10);
        vbox.setLayoutX(10.4 * (GRID_SIZE) + (2 * WINDOW_XOFFSET) + (GRID_DIMENSION * GRID_SIZE) + (0.5 * GRID_SIZE));
        vbox.setLayoutY(WINDOW_YOFFSET);

        controls.getChildren().add(vbox);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Arboretum Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

