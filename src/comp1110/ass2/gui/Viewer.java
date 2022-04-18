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
        var handA = new Text(0,100,"HandA: " + gameState[1][1]);
        var ArboretumA = new Text(300,50,"ArboretumA: ");
        var ArboretumB = new Text(300,400,"ArboretumB: " );
        var ArboretumA_Center = new Text(800,200,gameState[0][1].substring(1,3));
        var ArboretumB_Center = new Text(800,500,gameState[0][3].substring(1,3));
        var handB = new Text(0,600,"HandB: " + gameState[1][2]);
        var discardA = new Text(0,150,"DiscardA: "+ gameState[0][2]);
        var discardB = new Text(0,500,"DiscardB: "+ gameState[0][4]);
        var deck = new Text(0,350,"deck: "+ gameState[1][0] );
        root.getChildren().addAll(handA,ArboretumA,ArboretumB,handB,discardA,discardB,deck,ArboretumA_Center,ArboretumB_Center);
        int NSC =0;
        int EWC =0;
        int oneSquare = 20;

        String a = gameState[0][1].substring(1);
        for (int i = 0; i < a.substring(1).length(); i++) {
            if (a.substring(1) == "") break;
            if (i!=0 && i%8==0){
                int distanceNS = Integer.parseInt(a.substring(i+3,i+5));
                int distanceEW = Integer.parseInt(a.substring(i+6,i+8));
                if (a.charAt(i+2) == 'N') NSC = distanceNS;
                if (a.charAt(i+2) == 'S') NSC = -1*distanceNS;
                if (a.charAt(i+2) == 'C') NSC = 0;
                if (a.charAt(i+5) == 'E') EWC = distanceEW;
                if (a.charAt(i+5) == 'W') EWC = -1*distanceEW;
                if (a.charAt(i+5) == 'C') EWC = 0;
            var cardA = new Text(800+EWC*oneSquare,200-NSC*oneSquare,a.substring(i,i+2));
                root.getChildren().addAll(cardA);
            }
        }
        String b = gameState[0][3].substring(1);
        for (int i = 0; i < b.substring(1).length(); i++) {
            if (b.substring(1) == "") break;
            if (i!=0 && i%8==0){
                int distanceNS = Integer.parseInt(b.substring(i+3,i+5));
                int distanceEW = Integer.parseInt(b.substring(i+6,i+8));
                if (b.charAt(i+2) == 'N') NSC = distanceNS;
                if (b.charAt(i+2) == 'S') NSC = -1*distanceNS;
                if (a.charAt(i+2) == 'C') NSC = 0;
                if (b.charAt(i+5) == 'E') EWC = distanceEW;
                if (b.charAt(i+5) == 'W') EWC = -1*distanceEW;
                if (a.charAt(i+5) == 'C') EWC = 0;
                var cardB = new Text(800+EWC*oneSquare,500-NSC*oneSquare,b.substring(i,i+2));
                root.getChildren().addAll(cardB);
            }
        }
        // FIXME Task 6: implement the simple state viewer
    }

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

