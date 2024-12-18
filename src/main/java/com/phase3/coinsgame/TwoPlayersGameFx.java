package com.phase3.coinsgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TwoPlayersGameFx {
    private static int front=0, end=0;
    private static String name1, name2;
    private Button[] buttons;
    private Button name1Btn, name2Btn, coins1Btn, coins2Btn, replayBtn, mainReplayBtn, backBtn, backBtn2;
    private GridPane grid;
    private ScrollPane scrollPane;
    private Stage firstStage, winnerStg;
    private VBox player1Vb, player2Vb, mainVbox, winnerVb, mainVbox2;
    private HBox playersHb, coins1Hbox, coins2Hbox, winnerHb, mainHbox;
    private Label name1Label, name2Label, score1Lbl, score2Lbl, roundsLbl, winnerLbl;
    private int score1=0, score2=0, round=1, counter=0;

    public TwoPlayersGameFx() {
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        roundsLbl = new Label();
        roundsLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #DDDDDD");

        winnerStg = new Stage();
        winnerStg.setResizable(false);
        winnerVb = new VBox(20);
        winnerVb.setAlignment(Pos.CENTER);
        winnerVb.setStyle("-fx-background-color: #0A5048");
        winnerVb.setPadding(new Insets(20));
        winnerHb = new HBox(20);
        winnerHb.setAlignment(Pos.CENTER);
        winnerLbl = new Label();
        winnerLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #DDDDDD");
        replayBtn = new Button("Replay");
        HomeFx.normalStyle(replayBtn);
        replayBtn.setOnMouseEntered(e->HomeFx.hoverStyle(replayBtn));
        replayBtn.setOnMouseExited(e-> HomeFx.normalStyle(replayBtn));
        replayBtn.setOnAction(e->getMainHBox());
        backBtn2 = new Button("<- Back");
        backBtn2.setOnAction(e-> {
            NumbersFx numbersFx = new NumbersFx();
            Driver.getStackPane().getChildren().clear();
            Driver.getStackPane().getChildren().addAll(Driver.getImageView(), numbersFx.getVb());
        });
        HomeFx.normalStyle(backBtn2);
        backBtn2.setOnMouseEntered(e->HomeFx.hoverStyle(backBtn2));
        backBtn2.setOnMouseExited(e-> HomeFx.normalStyle(backBtn2));
        winnerHb.getChildren().addAll(backBtn2, replayBtn);
        winnerVb.getChildren().addAll(winnerLbl, winnerHb);
        winnerStg.setScene(new Scene(winnerVb));
        winnerStg.setTitle("Coins Game");
        winnerStg.getIcons().add(new Image("C:/Users/LaptopCenter/Desktop/images/logo.png"));

        grid.setMaxHeight(350);
        grid.setAlignment(Pos.CENTER);
        scrollPane = new ScrollPane(grid);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setMaxHeight(350);
        scrollPane.setMaxWidth(650);
        scrollPane.setPadding(new Insets(10, 10, 10, 10));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        HBox hBox = new HBox(30);
        VBox vbox = new VBox(30);
        Label whoFirstLbl = new Label("Who wants to go first?");
        whoFirstLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #DDDDDD");
        name1Btn = new Button(name1);
        name2Btn = new Button(name2);
        HomeFx.normalStyle(name1Btn);
        HomeFx.normalStyle(name2Btn);
        name1Btn.setOnMouseEntered(e->HomeFx.hoverStyle(name1Btn));
        name2Btn.setOnMouseEntered(e->HomeFx.hoverStyle(name2Btn));
        name1Btn.setOnMouseExited(e->HomeFx.normalStyle(name1Btn));
        name1Btn.setOnAction(e->{
            firstStage.close();
            roundsLbl.setText(name1+"'s Round");
        });
        name2Btn.setOnMouseExited(e->HomeFx.normalStyle(name2Btn));
        name2Btn.setOnAction(e->{
            String temp=name1;
            name1=name2;
            name2=temp;
            name1Label.setText(name1);
            name2Label.setText(name2);
            firstStage.close();
            roundsLbl.setText(name1+"'s Round");
        });
        hBox.getChildren().addAll(name1Btn, name2Btn);
        hBox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #0A5048");
        vbox.getChildren().addAll(whoFirstLbl, hBox);
        firstStage = new Stage();
        firstStage.setScene(new Scene(vbox));
        firstStage.setTitle("Coins Game");
        firstStage.getIcons().add(new Image("C:/Users/LaptopCenter/Desktop/images/logo.png"));
        firstStage.setResizable(false);

        player1Vb = new VBox(10);
        ImageView image1 = new ImageView("C:/Users/LaptopCenter/Desktop/images/person.png");
        image1.setFitHeight(100);
        image1.setFitWidth(100);
        name1Label = new Label(name1);
        name1Label.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #DDDDDD");
        score1Lbl = new Label("Score: 0");
        score1Lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #DDDDDD");
        coins1Btn = new Button("Show Coins");
        coins1Btn.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                "-fx-font-size: 16px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.5");
        coins1Btn.setOnMouseExited(e->{
            coins1Btn.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                    "-fx-font-size: 16px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.5");
        });
        coins1Btn.setOnMouseEntered(e->{
            coins1Btn.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                    "-fx-font-size: 16px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.8");
        });
        coins1Hbox = new HBox(10);
        coins1Hbox.setAlignment(Pos.CENTER);
        coins2Hbox = new HBox(10);
        coins2Hbox.setAlignment(Pos.CENTER);
        ScrollPane coinsScroll1 = new ScrollPane(coins1Hbox);
        coinsScroll1.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        ScrollPane coinsScroll2 = new ScrollPane(coins2Hbox);
        coinsScroll2.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        coinsScroll1.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        coinsScroll1.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        coinsScroll2.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        coinsScroll2.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        coinsScroll1.setMaxWidth(100);
        coinsScroll2.setMaxWidth(100);
        player1Vb.getChildren().addAll(image1, name1Label, score1Lbl, coinsScroll1);
        ImageView image2 = new ImageView("C:/Users/LaptopCenter/Desktop/images/person.png");
        image2.setFitHeight(100);
        image2.setFitWidth(100);
        name2Label = new Label(name2);
        name2Label.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-fill: #DDDDDD");
        coins2Btn = new Button("Show Coins");
        coins2Btn.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                "-fx-font-size: 16px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.5");
        coins2Btn.setOnMouseExited(e->{
            coins2Btn.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                    "-fx-font-size: 16px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.5");
        });
        coins2Btn.setOnMouseEntered(e->{
            coins2Btn.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                    "-fx-font-size: 16px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.8");
        });
        score2Lbl = new Label("Score: 0");
        score2Lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: #DDDDDD");
        player1Vb.setAlignment(Pos.CENTER);
        player2Vb = new VBox(10);
        player2Vb.setAlignment(Pos.CENTER);
        player2Vb.getChildren().addAll(image2, name2Label, score2Lbl, coinsScroll2);
        playersHb = new HBox(400);
        playersHb.setAlignment(Pos.CENTER);
        playersHb.getChildren().addAll(player1Vb, player2Vb);

        mainVbox = new VBox(20);
        mainVbox.setAlignment(Pos.CENTER);
        mainVbox.getChildren().addAll(roundsLbl, scrollPane, playersHb);

        mainVbox2 = new VBox(30);
        mainVbox2.setAlignment(Pos.CENTER);
        mainReplayBtn = new Button("Replay");
        HomeFx.normalStyle(mainReplayBtn);
        mainReplayBtn.setOnMouseEntered(e->HomeFx.hoverStyle(mainReplayBtn));
        mainReplayBtn.setOnMouseExited(e->HomeFx.normalStyle(mainReplayBtn));
        mainReplayBtn.setOnAction(e->getMainHBox());
        backBtn = new Button("<-Back");
        HomeFx.normalStyle(backBtn);
        backBtn.setOnMouseEntered(e->HomeFx.hoverStyle(backBtn));
        backBtn.setOnMouseExited(e->HomeFx.normalStyle(backBtn));
        backBtn.setOnAction(e-> {
            NumbersFx numbersFx = new NumbersFx();
            Driver.getStackPane().getChildren().clear();
            Driver.getStackPane().getChildren().addAll(Driver.getImageView(), numbersFx.getVb());
        });
        mainHbox = new HBox(100);
        mainHbox.setAlignment(Pos.CENTER);
        mainVbox2.getChildren().addAll(mainReplayBtn, backBtn);
        mainHbox.getChildren().addAll(mainVbox2, mainVbox);
    }

    private void fillButtons(){
        int n=Algorithms.coins.length;
        counter=front=0;
        end=n-1;
        grid.getChildren().clear();
        for(int i=0; i<n; i++) {
            buttons[i] = new Button(Algorithms.coins[i]+"");
            normalActiveButtonStyle(buttons[i]);
            int finalI = i, f2=i;
            if(i>0 && i<n-1) {
                buttons[i].setDisable(true);
                notActiveButtonStyle(buttons[i]);
            }
            buttons[i].setOnAction(e->{
                counter++;
                if(finalI ==front){
                    if(front+1!=end) front++;
                    buttons[front].setDisable(false);
                    normalActiveButtonStyle(buttons[front]);
                }
                else{
                    if(end-1!=front) end--;
                    buttons[end].setDisable(false);
                    normalActiveButtonStyle(buttons[end]);
                }
                buttons[finalI].setDisable(true);
                notActiveButtonStyle(buttons[finalI]);
                if(round==1){
                    score1+=Integer.parseInt(buttons[finalI].getText());
                    score1Lbl.setText("Score: "+score1);
                    Label label = new Label(buttons[finalI].getText());
                    label.setStyle("-fx-text-fill: #DDDDDD; -fx-font-weight: bold; -fx-font-size: 14px;");
                    coins1Hbox.getChildren().add(label);
                    roundsLbl.setText(name2+"'s Round");
                }
                else{
                    score2+=Integer.parseInt(buttons[finalI].getText());
                    score2Lbl.setText("Score: "+score2);
                    Label label = new Label(buttons[finalI].getText());
                    label.setStyle("-fx-text-fill: #DDDDDD; -fx-font-weight: bold; -fx-font-size: 14px;");
                    coins2Hbox.getChildren().add(label);
                    roundsLbl.setText(name1+"'s Round");
                }
                round*=-1;
                if(counter==n){
                    if(score1>score2)
                        winnerLbl.setText(name1+" is the WINNER!!");
                    else if(score2>score1)
                        winnerLbl.setText(name2+" is the WINNER!!");
                    else
                        winnerLbl.setText("It's a TIE!!");
                    winnerStg.show();
                }
            });
            grid.add(buttons[i], i%10, i/10);
        }
    }
    private void normalActiveButtonStyle(Button button){
        button.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                "-fx-font-size: 18px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.8");
    }
    private void hoverActiveButtonStyle(Button button){
        button.setStyle("-fx-background-color: white; -fx-text-fill: #28632c; -fx-font-weight: bold; " +
                "-fx-font-size: 18px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 1");

    }
    private void notActiveButtonStyle(Button button){
        button.setStyle("-fx-background-color: #28632c; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-font-size: 18px; -fx-border-radius: 10; -fx-background-radius: 10; -fx-opacity: 0.6");
    }

    public static String getName1(){
        return name1;
    }
    public static String getName2(){
        return name2;
    }
    public static void setName1(String name1){
        TwoPlayersGameFx.name1 = name1;
    }
    public static void setName2(String name2){
        TwoPlayersGameFx.name2 = name2;
    }
    public HBox getMainHBox(){
        int n=Algorithms.coins.length;
        buttons = new Button[n];
        fillButtons();
        name1Btn.setText(name1);
        name2Btn.setText(name2);
        firstStage.show();
        winnerStg.close();
        score1=score2=0;
        score1Lbl.setText("score:");
        score2Lbl.setText("score:");
        coins1Hbox.getChildren().clear();
        Label label = new Label("coins:");
        label.setStyle("-fx-text-fill: #DDDDDD; -fx-font-weight: bold; -fx-font-size: 14px;");
        coins1Hbox.getChildren().add(label);
        coins2Hbox.getChildren().clear();
        Label label2 = new Label("coins:");
        label2.setStyle("-fx-text-fill: #DDDDDD; -fx-font-weight: bold; -fx-font-size: 14px;");
        coins2Hbox.getChildren().add(label2);
        roundsLbl.setText(name1+"'s Round");
        return mainHbox;
    }
}
