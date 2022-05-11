package comp1110.ass2.gui;

import com.sun.media.jfxmedia.events.NewFrameEvent;
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
        var center = new Text(600,250,"Center");

        var hbox = new HBox();
        hbox.setLayoutX(593);
        hbox.setLayoutY(215);
        hbox.setPrefWidth(50);
        hbox.setPrefHeight(70);
        hbox.setStyle("-fx-padding: 10; -fx-background-color: cornsilk;");
        root.getChildren().add(hbox);

        root.getChildren().addAll(handA,Arboretum,handB,discardA,discardB,deck,Arboretum_Center,ID,center);


        //Add images
        for (int i = 0; i < 8; i++) {
            Image image = new Image(getClass().getResource("images/a"+(i+1)+".png").toURI().toString());
            ImageView iv = new ImageView(image);
            iv.setFitHeight(70);
            iv.setFitWidth(50);
            iv.setX(i * 50);
            iv.setY(560);
        //Set drag
            iv.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                   Dragboard cassia =  iv.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image);
                    cassia.setContent(content);
                }
            });

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


            root.getChildren().add(iv);

            Image image1 = new Image(getClass().getResource("images/b"+(i+1)+".png").toURI().toString());
            ImageView iv1 = new ImageView(image1);
            iv1.setFitHeight(70);
            iv1.setFitWidth(50);
            iv1.setX(i * 50);
            iv1.setY(630);

            iv1.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard blue =  iv1.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image1);
                    blue.setContent(content);
                }
            });
            root.getChildren().add(iv1);

            Image image2 = new Image(getClass().getResource("images/c"+(i+1)+".png").toURI().toString());
            ImageView iv2 = new ImageView(image2);
            iv2.setFitHeight(70);
            iv2.setFitWidth(50);
            iv2.setX(400+ i * 50);
            iv2.setY(630);

            iv2.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard cherry =  iv2.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image2);
                    cherry.setContent(content);
                }
            });
            root.getChildren().add(iv2);

            Image image3 = new Image(getClass().getResource("images/d"+(i+1)+".png").toURI().toString());
            ImageView iv3 = new ImageView(image3);
            iv3.setFitHeight(70);
            iv3.setFitWidth(50);
            iv3.setX(400+i * 50);
            iv3.setY(560);

            iv3.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard dogwood =  iv3.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image3);
                    dogwood.setContent(content);
                }
            });
            root.getChildren().add(iv3);

            Image image4 = new Image(getClass().getResource("images/j"+(i+1)+".png").toURI().toString());
            ImageView iv4 = new ImageView(image4);
            iv4.setFitHeight(70);
            iv4.setFitWidth(50);
            iv4.setX(800+i * 50);
            iv4.setY(560);

            iv4.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard jacaranda =  iv4.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image4);
                    jacaranda.setContent(content);
                }
            });
            root.getChildren().add(iv4);

            Image image5 = new Image(getClass().getResource("images/m"+(i+1)+".png").toURI().toString());
            ImageView iv5 = new ImageView(image5);
            iv5.setFitHeight(70);
            iv5.setFitWidth(50);
            iv5.setX(800+ i * 50);
            iv5.setY(630);

            iv5.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard maple =  iv5.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image5);
                    maple.setContent(content);
                }
            });
            root.getChildren().add(iv5);
        }

        // default
        stage.setScene(scene);
        stage.show();



    }
}