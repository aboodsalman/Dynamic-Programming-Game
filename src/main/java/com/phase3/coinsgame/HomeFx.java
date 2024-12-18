package com.phase3.coinsgame;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeFx {
    private Label nameLbl, descriptionLbl;
    private Button startBtn, exitBtn;
    private VBox homeVb;

    public HomeFx() {
        nameLbl = new Label("Coins Game");
        descriptionLbl = new Label("In this two-player game, players take turns choosing either the \n" +
                "first or last coin from a row, aiming to collect the highest total value. \n" +
                "The goal is to maximize your winnings while anticipating your opponent’s moves.\n" +
                "The game ends when all coins are taken, and the player with the most money wins.");
        nameLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 40px; -fx-text-fill: #DDDDDD");
        descriptionLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #DDDDDD; -fx-text-alignment: center");
        startBtn = new Button("Start");
        normalStyle(startBtn);
        startBtn.setOnMouseEntered(e->hoverStyle(startBtn));
        startBtn.setOnMouseExited(e->normalStyle(startBtn));
        startBtn.setOnAction(e -> {
            PlayersFx playersFx = new PlayersFx();
            Driver.getStackPane().getChildren().clear();
            Driver.getStackPane().getChildren().addAll(Driver.getImageView(), playersFx.getvBox());
        });

        exitBtn = new Button("Exit");
        normalStyle(exitBtn);
        exitBtn.setOnMouseEntered(e->hoverStyle(exitBtn));
        exitBtn.setOnMouseExited(e->normalStyle(exitBtn));
        exitBtn.setOnAction(e -> System.exit(0));

        HBox hb = new HBox(15);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(exitBtn, startBtn);

        homeVb = new VBox(20);
        homeVb.setAlignment(Pos.CENTER);
        homeVb.getChildren().addAll(nameLbl, descriptionLbl, hb);
    }

    public static void hoverStyle(Button button) {
        button.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                "-fx-font-size: 24px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.8");
    }
    public static void normalStyle(Button button) {
        button.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                "-fx-font-size: 24px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.5");
    }

    public VBox getHomeVb() {
        return homeVb;
    }
}
