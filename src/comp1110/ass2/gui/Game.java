package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Collection;

public class Game extends Application {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;
    private final Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {

        // FIXME Task 11: Implement a basic playable Arboretum game in JavaFX that only allows cards to be placed in
        //   valid places
        // FIXME Task 16: Implement a computer opponent so that a human can play your game against the computer.
        // FIXME Task 18: Implement variant(s).


        // Task 11, adding basic information for the panel
        stage.setTitle("Arboretum");
        //Group root = new Group(); I moved the root to outside the method at line 19
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        var ID = new Text(0,80,"Player Turn Id: ");
        var handA = new Text(0,100,"HandA: ");
        var Arboretum = new Text(0,50,"Arboretum: ");
        var Arboretum_Center = new Text(800,250,"");
        var handB = new Text(0,160,"HandB: ");
        var discardA = new Text(0,120,"DiscardA: ");
        var discardB = new Text(0,180,"DiscardB: ");
        var deck = new Text(0,140,"deck: ");
        root.getChildren().addAll(handA,Arboretum,handB,discardA,discardB,deck,Arboretum_Center,ID);




        //Add image
        Image image = new Image(getClass().getResource("images/a1.png").toURI().toString());
        ImageView iv = new ImageView(image);
        iv.setFitHeight(70);
        iv.setFitWidth(50);
        iv.setX(600);
        iv.setY(350);
        root.getChildren().add(iv);



        // default
        stage.setScene(scene);
        stage.show();
    }


    void displayState(String[][] gameState) throws Exception {
        // String [sharedState][hiddenState]
        // Shared : StringID   ArboretumA   DiscardA    ArboretumB    DiscardB
        // Hidden : Deck       HandA        HandB
        int NSC =0;
        int EWC =0;
        int oneSquare = 20;
        cardsPlacementGame(gameState,1,800,250);
        cardsPlacementGame(gameState,3,500,450);

    }

    void cardsPlacementGame(String[][] gameState,int Arboretum,int centerX,int centerY) throws Exception{

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

                Image image = new Image(getClass().getResource("images/"+a.substring(i,i+2)+".png").toURI().toString());
                int w = (int)image.getWidth();
                int h =(int)image.getHeight();
                ImageView iv = new ImageView(image);

                var cardAB = new Text(centerX+EWC*oneSquare,centerY-NSC*oneSquare,a.substring(i,i+2));
            }
        }
    }
}