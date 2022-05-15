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
import java.util.Collection;

public class Game extends Application {
    /* board layout */
    private static final int BOARD_WIDTH = 1200;
    private static final int BOARD_HEIGHT = 700;

    // Create the deck and player A
    Deck deck = new Deck();
    Players players = new Players(null,"A");



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
        var discard = new Text(1100,240,"Discard");
        var deck = new Text(0,555,"Deck: ");
        var center = new Text(600,255,"Center");

        /** Hbox for the center*/
        var hbox = new HBox();
        hbox.setLayoutX(593);
        hbox.setLayoutY(215);
        hbox.setPrefWidth(50);
        hbox.setPrefHeight(70);
        hbox.setStyle("-fx-padding: 10; -fx-background-color: cornsilk;");
        root.getChildren().add(hbox);

        root.getChildren().addAll(deck,center,discard);

        /** Add images for backside of cards*/
        Image imageB = new Image(getClass().getResource("images/Back.png").toURI().toString());
        ImageView Discard = new ImageView(imageB);
        Discard.setFitHeight(70);
        Discard.setFitWidth(50);
        Discard.setX(1100);
        Discard.setY(250);
        root.getChildren().add(Discard);

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

            Discard.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    dragEvent.acceptTransferModes(TransferMode.COPY);

                }
            });
            Discard.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    discard.setText(" Valid");
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