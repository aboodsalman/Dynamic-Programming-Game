package com.phase3.coinsgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Driver extends Application {

    private static StackPane stackPane;
    private static ImageView imageView; // The background image of the game

    @Override
    public void start(Stage stage) throws IOException {
        stackPane = new StackPane();
        imageView = new ImageView("C:/Users/LaptopCenter/Desktop/images/background.jpg");

        HomeFx homeFx = new HomeFx();

        stackPane.getChildren().addAll(imageView, homeFx.getHomeVb());

        Scene scene = new Scene(stackPane);

        stage.setScene(scene); // The main stage of the game
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.setTitle("Coins Game");
        stage.getIcons().add(new Image("C:/Users/LaptopCenter/Desktop/images/logo.png"));
        stage.show();
    }
    public static StackPane getStackPane() {
        return stackPane;
    }
    public static ImageView getImageView() {
        return imageView;
    }
}