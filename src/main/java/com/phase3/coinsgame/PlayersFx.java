package com.phase3.coinsgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;

public class PlayersFx {
    private Button onePlayerBtn, twoPlayerBtn, backBtn;
    private Label lbl;
    private VBox vBox;
    private Stage stage;
    private TextField name1Txt, name2Txt;
    public static int status;

    public PlayersFx() {
        stage = new Stage();
        name1Txt = new TextField();
        name1Txt.setFocusTraversable(false);
        name2Txt = new TextField();
        name2Txt.setFocusTraversable(false);
        Label stgLabel = new Label("Enter Players Names");
        stgLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: #DDDDDD;");
        Button continueBtn = new Button("Continue");
        continueBtn.setOnMouseEntered(e->HomeFx.hoverStyle(continueBtn));
        continueBtn.setOnMouseExited(e->HomeFx.normalStyle(continueBtn));
        continueBtn.setOnAction(e->{
            if(name1Txt.getText().isBlank() || name2Txt.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter Players Names");
                alert.showAndWait();
            }
            else if(name1Txt.getText().equals(name2Txt.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Players names should be different");
            }
            else{
                status=2;
                stage.close();
                TwoPlayersGameFx.setName1(name1Txt.getText());
                TwoPlayersGameFx.setName2(name2Txt.getText());
                NumbersFx numbersFx = new NumbersFx();
                Driver.getStackPane().getChildren().clear();
                Driver.getStackPane().getChildren().addAll(Driver.getImageView(), numbersFx.getVb());
            }
        });
        HomeFx.normalStyle(continueBtn);
        name1Txt.setPromptText("Player 1 Name");
        name1Txt.setStyle("-fx-text-fill: #DDDDDD; -fx-font-size: 20px; -fx-font-weight: bold; -fx-border-color: #DDDDDD;" +
                "-fx-background-color: #0A5048; -fx-border-radius: 10");
        name2Txt.setPromptText("Player 2 Name");
        name2Txt.setStyle("-fx-text-fill: #DDDDDD; -fx-font-size: 20px; -fx-font-weight: bold; -fx-border-color: #DDDDDD;" +
                "-fx-background-color: #0A5048; -fx-border-radius: 10");
        UnaryOperator<TextFormatter.Change> letterFilter = change -> {
            String text = change.getText();
            if (text.matches("[a-zA-Z]*")) return change;
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(letterFilter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(letterFilter);
        name1Txt.setTextFormatter(textFormatter);
        name2Txt.setTextFormatter(textFormatter2);
        ImageView image = new ImageView("C:/Users/LaptopCenter/Desktop/images/versus.png");
        image.setFitHeight(100);
        image.setFitWidth(100);
        VBox vb = new VBox(15);
        vb.getChildren().addAll(stgLabel ,name1Txt, image, name2Txt, continueBtn);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(15));
        vb.setStyle("-fx-background-color: #0A5048");
        stage.setTitle("Coins Game");
        stage.getIcons().add(new Image("C:/Users/LaptopCenter/Desktop/images/logo.png"));
        stage.setScene(new Scene(vb));
        stage.setResizable(false);

        lbl = new Label("How many players?");
        lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 40px; -fx-text-fill: #DDDDDD");

        onePlayerBtn = new Button("One Player");
        onePlayerBtn.setMinWidth(180);
        HomeFx.normalStyle(onePlayerBtn);
        onePlayerBtn.setOnMouseEntered(e->HomeFx.hoverStyle(onePlayerBtn));
        onePlayerBtn.setOnMouseExited(e->HomeFx.normalStyle(onePlayerBtn));
        onePlayerBtn.setOnAction(e->{
            status=1;
            OnePlayerGameFx.setName1("Computer");
            OnePlayerGameFx.setName2("Laptop");
            NumbersFx numbersFx = new NumbersFx();
            Driver.getStackPane().getChildren().clear();
            Driver.getStackPane().getChildren().addAll(Driver.getImageView(), numbersFx.getVb());
        });

        twoPlayerBtn = new Button("Two Players");
        twoPlayerBtn.setMinWidth(180);
        HomeFx.normalStyle(twoPlayerBtn);
        twoPlayerBtn.setOnMouseEntered(e->HomeFx.hoverStyle(twoPlayerBtn));
        twoPlayerBtn.setOnMouseExited(e->HomeFx.normalStyle(twoPlayerBtn));
        twoPlayerBtn.setOnAction(e->{
            stage.show();
        });

        backBtn = new Button("<- Back");
        backBtn.setOnAction(e->{
            HomeFx homeFx = new HomeFx();
            Driver.getStackPane().getChildren().clear();
            Driver.getStackPane().getChildren().addAll(Driver.getImageView(), homeFx.getHomeVb());
        });
        backBtn.setMinWidth(180);
        HomeFx.normalStyle(backBtn);
        backBtn.setOnMouseEntered(e->HomeFx.hoverStyle(backBtn));
        backBtn.setOnMouseExited(e->HomeFx.normalStyle(backBtn));

        vBox = new VBox(40);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(lbl, onePlayerBtn, twoPlayerBtn, backBtn);
    }

    public VBox getvBox(){
        name1Txt.setText("");
        name2Txt.setText("");
        return vBox;
    }
}
