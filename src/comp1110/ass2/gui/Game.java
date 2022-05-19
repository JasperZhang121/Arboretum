package comp1110.ass2.gui;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import comp1110.ass2.Arboretum;
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

/**
 * Author: Hao Zhang
 */

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
    String ArboretumA = "A";
    String ArboretumB = "B";

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

        /** Display basic information
         *  the text will be put on the hbox for showing information */
        var discardText = new Text(1095,265,"Discard: drop here!");
        var deckText = new Text(0,245,"Deck: draw here!");
        var placementGround = new Text(130,20,"Placement: drop here!");
        var handText = new Text(0,475,"Player A's hand: drop here!");
        root.getChildren().addAll(deckText,discardText,handText,placementGround);

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

        /** Add empty cards on hand A, which will be the valid places for storing cards from drawing
         *  Add drop event for hand A */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                var HandA = playerA.getEmptyCard();
                HandA.setX(i*55);
                HandA.setY(480+j*75);
                root.getChildren().add(HandA);

                HandA.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        dragEvent.acceptTransferModes(TransferMode.COPY);
                    }
                });
                HandA.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        var card = deck.drawCardFromDeck();
                        playerA.getCard(card);
                        gameState[0][1]=deck.toString();
                        try {
                            HandA.setImage(card.getImage());
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

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

        /** Add drop event for discard A and B*/
        ImageView [] discard_A_B = {DiscardA,DiscardB};
        for (var discard : discard_A_B){
            discard.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    dragEvent.acceptTransferModes(TransferMode.COPY);
                }
            });
            discard.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    var card = deck.drawCardFromDeck();
                    gameState[0][1]=deck.toString();
                }
            });
        }

        /** Add placement ground
         *  Add drop event for the ground
         *  */
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 9; j++) {
                var EmptyCards = playerA.getEmptyCard();
                EmptyCards.setX(250+i*55);
                EmptyCards.setY(j * 75);
                root.getChildren().add(EmptyCards);

                EmptyCards.setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        dragEvent.acceptTransferModes(TransferMode.COPY);
                    }
                });
                EmptyCards.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        var card = deck.drawCardFromDeck();
                        playerA.getCard(card);
                        gameState[1][0]=deck.toString();
                        try {
                            EmptyCards.setImage(card.getImage());
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

        /** Add images for all cards on the scene - not useful, comment out*/
        /*for (int i = 0; i < 8; i++) {
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

            *//** Set drag events
             *  All cards are draggable*//*
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


            *//** Set on Drop*//*
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
        }*/
    }
}