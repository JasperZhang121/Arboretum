package comp1110.ass2.gui;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import comp1110.ass2.Player;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

public class Game extends Application {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;

    // Create Objects for the game (deck, playerA, playerB, discardA, discardB, ArboretumA, ArboretumB)
    Deck deck = new Deck();
    Players playerA = new Players(null,"A");
    Players playerB = new Players(null,"B");
    Discard discardA= new Discard(null,"A");
    Discard discardB= new Discard(null,"B");
    String ArboretumA = "";
    String ArboretumB = "";


    // Create the start gameState, assume start with A player
    // String [sharedState][hiddenState]
    // Shared : StringID   ArboretumA   DiscardA    ArboretumB    DiscardB
    // Hidden : Deck       HandA        HandB
    String[] shared = {playerA.getName(), ArboretumA, discardA.toString(), ArboretumB,discardB.toString()};
    String[] hidden = { deck.toString(), playerA.toString(), playerB.toString()};
    String[][] gameState = new String[][]{shared,hidden};


    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Arboretum");
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        stage.setScene(scene);
        stage.show();

        // FIXME Task 11: Implement a basic playable Arboretum game in JavaFX that only allows cards to be placed in
        //   valid places
        // FIXME Task 16: Implement a computer opponent so that a human can play your game against the computer.
        // FIXME Task 18: Implement variant(s).


        var cards = new Cards("a","1");
        cards.getCardImage();
        root.getChildren().add(cards.getCardImage());


        /** Display basic information
         *  the text will be put on the hbox for showing information */
        var discardText = new Text(1100,265,"Discard:");
        var deckText = new Text(0,245,"Deck: ");
        var center = new Text(600,255,"Center");
        var handText = new Text(0,395,"Player A:");

        /** Hbox for the center*/
        var hbox = new HBox();
        hbox.setLayoutX(593);
        hbox.setLayoutY(215);
        hbox.setPrefWidth(50);
        hbox.setPrefHeight(70);
        hbox.setStyle("-fx-padding: 10; -fx-background-color: cornsilk;");
        root.getChildren().add(hbox);
        root.getChildren().addAll(deckText,center,discardText,handText);

        /** Add discardA, put the back of card on scene */
        ImageView DiscardA = discardA.getBackOfCard();
        DiscardA.setFitHeight(70);
        DiscardA.setFitWidth(50);
        DiscardA.setX(1150);
        DiscardA.setY(275);
        root.getChildren().add(DiscardA);

        /** Add discardB, put the back of card on scene*/
        ImageView DiscardB = discardA.getBackOfCard();
        DiscardB.setFitHeight(70);
        DiscardB.setFitWidth(50);
        DiscardB.setX(1150);
        DiscardB.setY(175);
        root.getChildren().add(DiscardB);

        /** Add the deck, put the back of card of scene */
        ImageView Deck = deck.getBackOfCard();
        Deck.setFitHeight(70);
        Deck.setFitWidth(50);
        Deck.setX(0);
        Deck.setY(250);
        root.getChildren().add(Deck);

        /** Add empty cards on hand, which will be the valid places for storing cards from drawing*/
        var firstOnHand = playerA.getEmptyCard();
        firstOnHand.setX(0);
        firstOnHand.setY(400);

        var secondOnHand = playerA.getEmptyCard();
        secondOnHand.setX(55);
        secondOnHand.setY(400);

        var thirdOnHand = playerA.getEmptyCard();
        thirdOnHand.setX(110);
        thirdOnHand.setY(400);

        var forthOnHand = playerA.getEmptyCard();
        forthOnHand.setX(165);
        forthOnHand.setY(400);

        var fifthOnHand = playerA.getEmptyCard();
        fifthOnHand.setX(220);
        fifthOnHand.setY(400);

        var sixthOnHand = playerA.getEmptyCard();
        sixthOnHand.setX(0);
        sixthOnHand.setY(475);

        var seventhOnHand =playerA.getEmptyCard();
        seventhOnHand.setX(55);
        seventhOnHand.setY(475);

        var eighthOnHand = playerA.getEmptyCard();
        eighthOnHand.setX(110);
        eighthOnHand.setY(475);

        var ninthOnHand= playerA.getEmptyCard();
        ninthOnHand.setX(165);
        ninthOnHand.setY(475);

        root.getChildren().addAll(firstOnHand,secondOnHand,thirdOnHand,forthOnHand,fifthOnHand,sixthOnHand,seventhOnHand,eighthOnHand,ninthOnHand);





        /** Add drag event for deck*/
        Deck.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Dragboard deckPng =  Deck.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                Image image = null;
                try {
                    image = new Image(getClass().getResource("images/Back.png").toURI().toString());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                content.putImage(image);
                deckPng.setContent(content);
            }
        });

        /** Add drop event for hand */
        ImageView [] handALL = {firstOnHand,secondOnHand,thirdOnHand,forthOnHand,fifthOnHand,sixthOnHand,seventhOnHand,eighthOnHand,ninthOnHand};
        for (var hand : handALL){
            hand.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    dragEvent.acceptTransferModes(TransferMode.COPY);
                }
            });
            hand.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    var card = deck.drawCardFromDeck();
                    try {
                        hand.setImage(card.getImage());
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            });
        }





















        /** Add images for all cards on the scene*/
        for (int i = 0; i < 8; i++) {
            Image image = new Image(getClass().getResource("images/a"+(i+1)+".png").toURI().toString());
            ImageView iv = new ImageView(image);
            iv.setFitHeight(70);
            iv.setFitWidth(50);
            iv.setX(i * 50);
            iv.setY(560);
            root.getChildren().add(iv);

            Image image1 = new Image(getClass().getResource("images/b"+(i+1)+".png").toURI().toString());
            ImageView iv1 = new ImageView(image1);
            iv1.setFitHeight(70);
            iv1.setFitWidth(50);
            iv1.setX(i * 50);
            iv1.setY(630);
            root.getChildren().add(iv1);

            Image image2 = new Image(getClass().getResource("images/c"+(i+1)+".png").toURI().toString());
            ImageView iv2 = new ImageView(image2);
            iv2.setFitHeight(70);
            iv2.setFitWidth(50);
            iv2.setX(400+ i * 50);
            iv2.setY(630);
            root.getChildren().add(iv2);

            Image image3 = new Image(getClass().getResource("images/d"+(i+1)+".png").toURI().toString());
            ImageView iv3 = new ImageView(image3);
            iv3.setFitHeight(70);
            iv3.setFitWidth(50);
            iv3.setX(400+i * 50);
            iv3.setY(560);
            root.getChildren().add(iv3);

            Image image4 = new Image(getClass().getResource("images/j"+(i+1)+".png").toURI().toString());
            ImageView iv4 = new ImageView(image4);
            iv4.setFitHeight(70);
            iv4.setFitWidth(50);
            iv4.setX(800+i * 50);
            iv4.setY(560);
            root.getChildren().add(iv4);

            Image image5 = new Image(getClass().getResource("images/m"+(i+1)+".png").toURI().toString());
            ImageView iv5 = new ImageView(image5);
            iv5.setFitHeight(70);
            iv5.setFitWidth(50);
            iv5.setX(800+ i * 50);
            iv5.setY(630);
            root.getChildren().add(iv5);

            /** Set drag events
             *  All cards are draggable*/
            iv.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard cassia =  iv.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image);
                    cassia.setContent(content);
                }
            });
            iv1.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard blue =  iv1.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image1);
                    blue.setContent(content);
                }
            });
            iv2.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard cherry =  iv2.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image2);
                    cherry.setContent(content);
                }
            });
            iv3.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard dogwood =  iv3.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image3);
                    dogwood.setContent(content);
                }
            });
            iv4.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard jacaranda =  iv4.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image4);
                    jacaranda.setContent(content);
                }
            });
            iv5.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard maple =  iv5.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image5);
                    maple.setContent(content);
                }
            });


            /** Set on Drop*/
            center.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    dragEvent.acceptTransferModes(TransferMode.COPY);
                }
            });

            center.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    center.setText("  First");

                }
            });

            DiscardA.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    dragEvent.acceptTransferModes(TransferMode.COPY);

                }
            });
            DiscardA.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    discardText.setText(" Valid");
                    iv.setImage(null);

                }
            });

            hbox.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    dragEvent.acceptTransferModes(dragEvent.getTransferMode());
                }
            });
            hbox.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    Dragboard db =dragEvent.getDragboard();
                    Image image = db.getImage();

                }
            });
        }



    }


}