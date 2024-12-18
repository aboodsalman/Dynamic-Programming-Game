package com.phase3.coinsgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.UnaryOperator;

/* This class is for the fx design for the screen of entering the coins numbers, either manually,
randomly, or from a file. */
public class NumbersFx {
    private Label lbl, fromLbl, toLbl, stgLbl, numLbl, numLbl2, numberLbl;
    private Button manualBtn, randomBtn, fileBtn, back, continueBtn;
    private FileChooser fileChooser;
    private VBox vb, stgVbRandom, stgVbManual;
    private TextField fromTxt, toTxt, numTxt, numTxt2, numberTxt;
    private Stage randomStg, manualStg;
    private HBox addNumbersHb, numHb2, numbersHb;
    private int i=0;
    private File file;

    public NumbersFx() {
        randomStg = new Stage();
        stgVbRandom = new VBox(20);
        stgVbRandom.setAlignment(Pos.CENTER);
        stgVbRandom.setPadding(new Insets(20));
        stgVbRandom.setStyle("-fx-background-color: #0A5048");
        stgLbl = new Label("Enter the number and range of Coins");
        stgLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #DDDDDD");
        numLbl = new Label("Enter the number of Coins: ");
        numLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #DDDDDD");
        numTxt = new TextField();
        numTxt.setStyle("-fx-text-fill: #DDDDDD; -fx-font-size: 15px; -fx-font-weight: bold; -fx-border-color: #DDDDDD;" +
                "-fx-background-color: #0A5048; -fx-border-radius: 10");
        HBox numHb = new HBox(15);
        numHb.getChildren().addAll(numLbl, numTxt);
        fromLbl = new Label("From");
        fromLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #DDDDDD");
        toLbl = new Label("To");
        toLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #DDDDDD");
        fromTxt = new TextField();
        fromTxt.setStyle("-fx-text-fill: #DDDDDD; -fx-font-size: 15px; -fx-font-weight: bold; -fx-border-color: #DDDDDD;" +
                "-fx-background-color: #0A5048; -fx-border-radius: 10");
        toTxt = new TextField();
        toTxt.setStyle("-fx-text-fill: #DDDDDD; -fx-font-size: 15px; -fx-font-weight: bold; -fx-border-color: #DDDDDD;" +
                "-fx-background-color: #0A5048; -fx-border-radius: 10");
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(fromLbl, fromTxt, toLbl, toTxt);
        continueBtn = new Button("Continue");
        continueBtn.setMinWidth(180);
        HomeFx.normalStyle(continueBtn);
        continueBtn.setOnMouseEntered(e->HomeFx.hoverStyle(continueBtn));
        continueBtn.setOnMouseExited(e->HomeFx.normalStyle(continueBtn));
        continueBtn.setOnAction(e->{
            if(fromTxt.getText().isBlank() || toTxt.getText().isBlank() || numTxt.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter all the fields correctly.");
                alert.showAndWait();
            }
            else{
                int l=Integer.parseInt(fromTxt.getText()), r=Integer.parseInt(toTxt.getText());
                int num=Integer.parseInt(numTxt.getText());
                if(l > r) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("The entered range isn't valid, try again.");
                    alert.showAndWait();
                }
                else if(num==0 || num%2==1 || num>200){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an even positive number less than or equal to 200.");
                    alert.showAndWait();
                }
                else{
                    Algorithms.coins = new int[num];
                    for(int i=0; i<num; i++){
                        Algorithms.coins[i]=(int)(Math.random()*(r-l+1)) + l;
                    }
                    randomStg.close();
                    if(PlayersFx.status==2){
                        TwoPlayersGameFx twoPlayersGameFx = new TwoPlayersGameFx();
                        Driver.getStackPane().getChildren().clear();
                        Driver.getStackPane().getChildren().addAll(Driver.getImageView(), twoPlayersGameFx.getMainHBox());
                    }
                    else{
                        OnePlayerGameFx onePlayerGameFx = new OnePlayerGameFx();
                        Algorithms.findMaxCoins();
                        Driver.getStackPane().getChildren().clear();
                        Driver.getStackPane().getChildren().addAll(Driver.getImageView(), onePlayerGameFx.getMainHBox());
                    }
                }
            }
        });
        stgVbRandom.getChildren().addAll(stgLbl,numHb, hb, continueBtn);
        Scene scene = new Scene(stgVbRandom);
        stgVbRandom.setAlignment(Pos.CENTER);
        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String text = change.getText();
            String currentText = change.getControlNewText();
            if(text.matches("[0-9]*")) {
                if(!currentText.isEmpty() && currentText.startsWith("0"))
                    return null;

                if (currentText.length()<=5)
                    return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(numberFilter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(numberFilter);
        TextFormatter<String> textFormatter3 = new TextFormatter<>(numberFilter);
        fromTxt.setTextFormatter(textFormatter);
        toTxt.setTextFormatter(textFormatter2);
        numTxt.setTextFormatter(textFormatter3);
        randomStg.setResizable(false);
        randomStg.setTitle("Coins Game");
        randomStg.getIcons().add(new Image("C:/Users/LaptopCenter/Desktop/images/logo.png"));
        randomStg.setScene(scene);

        manualStg = new Stage();
        stgVbManual = new VBox(20);
        stgVbManual.setAlignment(Pos.CENTER);
        stgVbManual.setPadding(new Insets(20));
        stgVbManual.setStyle("-fx-background-color: #0A5048");
        numLbl2 = new Label("Enter the number of Coins: ");
        numLbl2.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #DDDDDD");
        numberLbl = new Label();
        numberLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #DDDDDD");
        numTxt2 = new TextField();
        numTxt2.setStyle("-fx-text-fill: #DDDDDD; -fx-font-size: 15px; -fx-font-weight: bold; -fx-border-color: #DDDDDD;" +
                "-fx-background-color: #0A5048; -fx-border-radius: 10");
        numberTxt = new TextField();
        numberTxt.setStyle("-fx-text-fill: #DDDDDD; -fx-font-size: 15px; -fx-font-weight: bold; -fx-border-color: #DDDDDD;" +
                "-fx-background-color: #0A5048; -fx-border-radius: 10");
        numHb2 = new HBox(15);
        numbersHb = new HBox(10);
        addNumbersHb = new HBox(10);
        numHb2.getChildren().addAll(numLbl2, numTxt2);
        addNumbersHb.getChildren().addAll(numberLbl, numberTxt);
        stgVbManual.getChildren().addAll(numHb2);
        Scene scene2 = new Scene(stgVbManual);
        stgVbManual.setAlignment(Pos.CENTER);
        TextFormatter<String> textFormatter4 = new TextFormatter<>(numberFilter);
        TextFormatter<String> textFormatter5 = new TextFormatter<>(numberFilter);
        numTxt2.setTextFormatter(textFormatter4);
        numberTxt.setTextFormatter(textFormatter5);
        numTxt2.setOnAction(e->numbersValidationHandler());
        numberTxt.setOnAction(e->numbersHandler());
        manualStg.setResizable(false);
        manualStg.setTitle("Coins Game");
        manualStg.getIcons().add(new Image("C:/Users/LaptopCenter/Desktop/images/logo.png"));
        manualStg.setScene(scene2);

        lbl = new Label("How to fill the array?");
        lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 40px; -fx-text-fill: #DDDDDD");

        manualBtn = new Button("Manually");
        manualBtn.setMinWidth(180);
        HomeFx.normalStyle(manualBtn);
        manualBtn.setOnMouseEntered(e->HomeFx.hoverStyle(manualBtn));
        manualBtn.setOnMouseExited(e->HomeFx.normalStyle(manualBtn));
        manualBtn.setOnAction(e->{
            getVb();
            manualStg.show();
        });

        randomBtn = new Button("Randomly");
        randomBtn.setMinWidth(180);
        HomeFx.normalStyle(randomBtn);
        randomBtn.setOnMouseEntered(e->HomeFx.hoverStyle(randomBtn));
        randomBtn.setOnMouseExited(e->HomeFx.normalStyle(randomBtn));
        randomBtn.setOnAction(e->randomStg.show());

        back = new Button("<- Back");
        back.setMinWidth(180);
        HomeFx.normalStyle(back);
        back.setOnMouseEntered(e->HomeFx.hoverStyle(back));
        back.setOnMouseExited(e->HomeFx.normalStyle(back));
        back.setOnAction(e->{
            PlayersFx playersFx = new PlayersFx();
            Driver.getStackPane().getChildren().clear();
            Driver.getStackPane().getChildren().addAll(Driver.getImageView(), playersFx.getvBox());
        });

        fileChooser = new FileChooser();
        fileBtn = new Button("From File");
        fileBtn.setMinWidth(180);
        HomeFx.normalStyle(fileBtn);
        fileBtn.setOnMouseEntered(e->HomeFx.hoverStyle(fileBtn));
        fileBtn.setOnMouseExited(e->HomeFx.normalStyle(fileBtn));
        Stage stg = new Stage();
        fileBtn.setOnAction(e->{

            file = fileChooser.showOpenDialog(stg);
            try {
                readFromFile();
            } catch (NullPointerException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a file");
            }
        });

        vb = new VBox(40);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(lbl, manualBtn, randomBtn, fileBtn, back);
    }

    public VBox getVb() {
        i=0;
        numTxt2.setText("");
        numTxt2.setDisable(false);
        numberTxt.setDisable(false);
        numberTxt.setText("");
        numbersHb.getChildren().clear();
        stgVbManual.getChildren().clear();
        stgVbManual.getChildren().add(numHb2);
        return vb;
    }
    private void numbersValidationHandler() {
        if (numTxt2.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an even positive number.");
            alert.showAndWait();
        } else {
            int num = Integer.parseInt(numTxt2.getText());
            if (num == 0 || num % 2 == 1 || num>200) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please enter an even positive number < 200.");
                alert.showAndWait();
            } else {
                Algorithms.coins = new int[num];
                numTxt2.setDisable(true);
                stgVbManual.getChildren().clear();
                numberLbl.setText("Please enter number #1: ");
                manualStg.setMinHeight(240);
                stgVbManual.getChildren().addAll(numHb2, addNumbersHb, numbersHb);
            }
        }
    }
    private void numbersHandler(){
        if (numberTxt.getText().isBlank() || numberTxt.getText().charAt(0)=='0') {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a positive integer");
            alert.showAndWait();
        } else {
            int num = Integer.parseInt(numTxt2.getText()), element=Integer.parseInt(numberTxt.getText());
            Algorithms.coins[i++]=element;
            Label nLbl = new Label(numberTxt.getText());
            nLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #DDDDDD");
            if(i==1){
                nLbl.setText("The numbers are: "+numberTxt.getText());
            }
            numbersHb.getChildren().add(nLbl);
            numberTxt.setText("");
            if(i==num) {
                numberTxt.setDisable(true);
                Button continueBtn = new Button("Continue");
                continueBtn.setOnMouseEntered(e->HomeFx.hoverStyle(continueBtn));
                continueBtn.setOnMouseExited(e->HomeFx.normalStyle(continueBtn));
                continueBtn.setOnAction(e->{
                    if(PlayersFx.status==2){
                        manualStg.close();
                        TwoPlayersGameFx twoPlayersGameFx = new TwoPlayersGameFx();
                        Driver.getStackPane().getChildren().clear();
                        Driver.getStackPane().getChildren().addAll(Driver.getImageView(), twoPlayersGameFx.getMainHBox());
                    }
                    else{
                        OnePlayerGameFx onePlayerGameFx = new OnePlayerGameFx();
                        Algorithms.findMaxCoins();
                        Driver.getStackPane().getChildren().clear();
                        Driver.getStackPane().getChildren().addAll(Driver.getImageView(), onePlayerGameFx.getMainHBox());
                    }
                    manualStg.close();
                });
                HomeFx.normalStyle(continueBtn);
                manualStg.setMinHeight(300);
                stgVbManual.getChildren().add(continueBtn);
            }
            else numberLbl.setText("Please enter number #"+(i+1)+": ");
        }
    }
    private void readFromFile() {
        try {
            Scanner scan = new Scanner(new File(file.toString()));
            int n=0, i=0, x;
            if (scan.hasNext())
                n=Integer.parseInt(scan.next());
            if(n==0 || n%2==1 || n>200) throw new IOException();
            Algorithms.coins=new int[n];
            while (scan.hasNext() && n-->0) {
                x = Integer.parseInt(scan.next());
                Algorithms.coins[i++]=x;
                if(x<=0) throw new IOException();
            }
            if(n!=0) throw  new IOException();
            scan.close();
            if(PlayersFx.status==2){
                manualStg.close();
                TwoPlayersGameFx twoPlayersGameFx = new TwoPlayersGameFx();
                Driver.getStackPane().getChildren().clear();
                Driver.getStackPane().getChildren().addAll(Driver.getImageView(), twoPlayersGameFx.getMainHBox());
            }
            else{
                OnePlayerGameFx onePlayerGameFx = new OnePlayerGameFx();
                Algorithms.findMaxCoins();
                Driver.getStackPane().getChildren().clear();
                Driver.getStackPane().getChildren().addAll(Driver.getImageView(), onePlayerGameFx.getMainHBox());
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a valid file, The first number should an even positive " +
                    "integer, and there's no coin with a non-positive value");
            alert.show();
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The file should contain only numbers");
            alert.show();
        }
    }
}
