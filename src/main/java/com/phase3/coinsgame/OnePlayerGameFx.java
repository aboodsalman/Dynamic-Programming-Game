package com.phase3.coinsgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OnePlayerGameFx {
    private static int front=0, end=0;
    private static String name1, name2;
    private Button[] buttons;
    private Button name1Btn, name2Btn, coins1Btn, coins2Btn, replayBtn, backStgBtn, mainReplayBtn, backBtn,
    stopBtn, resumeBtn, nextBtn, dpTableBtn, resultBtn;
    private GridPane grid;
    private ScrollPane scrollPane;
    private Stage firstStage, winnerStg, dpStg;
    private VBox player1Vb, player2Vb, mainVbox, winnerVb, mainVbox2;
    private HBox playersHb, coins1Hbox, coins2Hbox, winnerHb, mainHbox, btnHb;
    private Label name1Label, name2Label, score1Lbl, score2Lbl, roundsLbl, winnerLbl;
    private int score1=0, score2=0, round=1, counter=0, n;
    private Timeline timeline;
    private GridPane gridPaneTable;

    public OnePlayerGameFx() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextAction();
                if (counter >= n) {
                    timeline.stop();
                    dpTableBtn.setDisable(false);
                }
            }
        }));

        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        gridPaneTable = new GridPane();

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
        backStgBtn = new Button("<- back");
        HomeFx.normalStyle(backStgBtn);
        backStgBtn.setOnMouseEntered(e->HomeFx.hoverStyle(backStgBtn));
        backStgBtn.setOnMouseExited(e-> HomeFx.normalStyle(backStgBtn));
        backStgBtn.setOnAction(e->{
            winnerStg.close();
            NumbersFx numbersFx = new NumbersFx();
            Driver.getStackPane().getChildren().clear();
            Driver.getStackPane().getChildren().addAll(Driver.getImageView(), numbersFx.getVb());
        });
        winnerHb.getChildren().addAll(backStgBtn, replayBtn);
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
        nextBtn = new Button("Next");
        nextBtn.setOnAction(e->{
            if(counter!=n) {
                nextAction();
            }
        });
        HomeFx.normalStyle(nextBtn);
        nextBtn.setOnMouseEntered(e->HomeFx.hoverStyle(nextBtn));
        nextBtn.setOnMouseExited(e->HomeFx.normalStyle(nextBtn));
        btnHb = new HBox(10);
        btnHb.setAlignment(Pos.CENTER);
        playersHb = new HBox(180);
        playersHb.setAlignment(Pos.CENTER);
        playersHb.getChildren().addAll(player1Vb, nextBtn, player2Vb);

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
        stopBtn = new Button("Stop");
        stopBtn.setOnAction(e->{
            resumeBtn.setDisable(false);
            stopBtn.setDisable(true);
            timeline.stop();
        });
        HomeFx.normalStyle(stopBtn);
        stopBtn.setOnMouseEntered(e->HomeFx.hoverStyle(stopBtn));
        stopBtn.setOnMouseExited(e->HomeFx.normalStyle(stopBtn));
        resumeBtn = new Button("Start");
        resumeBtn.setOnAction(e->{
            if(counter<n) {
                resumeBtn.setDisable(true);
                stopBtn.setDisable(false);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        });
        HomeFx.normalStyle(resumeBtn);
        resumeBtn.setOnMouseEntered(e->HomeFx.hoverStyle(resumeBtn));
        resumeBtn.setOnMouseExited(e->HomeFx.normalStyle(resumeBtn));
        stopBtn.setDisable(true);
        resumeBtn.setDisable(false);
        dpTableBtn = new Button("Show Table");
        dpTableBtn.setDisable(true);
        HomeFx.normalStyle(dpTableBtn);
        dpTableBtn.setOnMouseEntered(e->HomeFx.hoverStyle(dpTableBtn));
        dpTableBtn.setOnMouseExited(e->HomeFx.normalStyle(dpTableBtn));
        dpTableBtn.setOnAction(e->dpStg.show());
        backBtn = new Button("<- Back");
        HomeFx.normalStyle(backBtn);
        backBtn.setOnMouseEntered(e->HomeFx.hoverStyle(backBtn));
        backBtn.setOnMouseExited(e->HomeFx.normalStyle(backBtn));
        backBtn.setOnAction(e-> {
            NumbersFx numbersFx = new NumbersFx();
            Driver.getStackPane().getChildren().clear();
            Driver.getStackPane().getChildren().addAll(Driver.getImageView(), numbersFx.getVb());
        });
        resultBtn = new Button("Get Result");
        resultBtn.setOnAction(e->{
            while(counter<n) nextAction();
        });
        HomeFx.normalStyle(resultBtn);
        resultBtn.setOnMouseEntered(e->HomeFx.hoverStyle(resultBtn));
        resultBtn.setOnMouseExited(e->HomeFx.normalStyle(resultBtn));
        resultBtn.setPrefWidth(165);
        backBtn.setPrefWidth(165);
        resumeBtn.setPrefWidth(165);
        stopBtn.setPrefWidth(165);
        dpTableBtn.setPrefWidth(165);
        mainReplayBtn.setPrefWidth(165);
        mainHbox = new HBox(100);
        mainHbox.setAlignment(Pos.CENTER);
        mainVbox2.getChildren().addAll(mainReplayBtn, resultBtn, stopBtn, resumeBtn, dpTableBtn, backBtn);
        mainHbox.getChildren().addAll(mainVbox2, mainVbox);
    }

    private void fillButtons(){
        n=Algorithms.coins.length;
        counter=front=0;
        end=n-1;
        grid.getChildren().clear();
        for(int i=0; i<n; i++) {
            buttons[i] = new Button(Algorithms.coins[i]+"");
            normalActiveButtonStyle(buttons[i]);
            if(i>0 && i<n-1) {
                buttons[i].setDisable(true);
                notActiveButtonStyle(buttons[i]);
            }
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
        OnePlayerGameFx.name1 = name1;
    }
    public static void setName2(String name2){
        OnePlayerGameFx.name2 = name2;
    }
    public HBox getMainHBox(){
        n=Algorithms.coins.length;
        drawTable();
        buttons = new Button[n];
        fillButtons();
        stopBtn.setDisable(true);
        resumeBtn.setDisable(false);
        dpTableBtn.setDisable(true);
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

    private void nextAction(){
        byte firstCoinPicked[][] = Algorithms.getFirstCoinPicked();
        counter++;
        int index;
        if (firstCoinPicked[front][end] == 1) {
            index = front;
            front++;
            if(front<n) {
                buttons[front].setDisable(false);
                normalActiveButtonStyle(buttons[front]);
            }
        } else {
            index = end;
            end--;
            if(end>=0) {
                buttons[end].setDisable(false);
                normalActiveButtonStyle(buttons[end]);
            }
        }
        buttons[index].setDisable(true);
        notActiveButtonStyle(buttons[index]);
        if (round == 1) {
            score1 += Integer.parseInt(buttons[index].getText());
            score1Lbl.setText("Score: " + score1);
            Label label = new Label(buttons[index].getText());
            label.setStyle("-fx-text-fill: #DDDDDD; -fx-font-weight: bold; -fx-font-size: 14px;");
            coins1Hbox.getChildren().add(label);
            roundsLbl.setText(name2 + "'s Round");
        } else {
            score2 += Integer.parseInt(buttons[index].getText());
            score2Lbl.setText("Score: " + score2);
            Label label = new Label(buttons[index].getText());
            label.setStyle("-fx-text-fill: #DDDDDD; -fx-font-weight: bold; -fx-font-size: 14px;");
            coins2Hbox.getChildren().add(label);
            roundsLbl.setText(name1 + "'s Round");
        }
        round *= -1;
        if (counter == n) {
            if (score1 > score2)
                winnerLbl.setText(name1 + " is the WINNER!!");
            else if (score2 > score1)
                winnerLbl.setText(name2 + " is the WINNER!!");
            else
                winnerLbl.setText("It's a TIE!!");
            dpTableBtn.setDisable(false);
            winnerStg.show();
        }
    }
    private void drawTable(){
        gridPaneTable.getChildren().clear();
        gridPaneTable.setStyle("-fx-padding: 10; -fx-hgap: 5; -fx-vgap: 5;");
        gridPaneTable.add(new Label("DP TABLE"), 0, 0);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPaneTable);
        scrollPane.setMaxWidth(600);
        scrollPane.setMaxHeight(600);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        int dp[][]=Algorithms.getDp();

        for(int i=1; i<=n; i++){
            Label cell = createCellBar(Algorithms.coins[i-1]);
            gridPaneTable.add(cell, i, 0);
        }
        for(int i=1; i<=n; i++){
            Label cell = createCellBar(Algorithms.coins[i-1]);
            gridPaneTable.add(cell, 0, i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Label cell = createCell(dp[i][j]);
                gridPaneTable.add(cell, j+1, i+1);
            }
        }

        Scene scene = new Scene(scrollPane);
        gridPaneTable.setAlignment(Pos.CENTER);
        gridPaneTable.setPadding(new Insets(20));
        dpStg = new Stage();
        dpStg.setResizable(false);
        dpStg.setScene(scene);
        dpStg.setTitle("Coins Game");
        dpStg.getIcons().add(new Image("C:/Users/LaptopCenter/Desktop/images/logo.png"));
    }
    private Label createCell(int value) {
        Label label = new Label(String.valueOf(value));
        label.setFont(Font.font("Arial", 16));
        label.setTextFill(Color.BLACK);
        label.setMinSize(50, 50);
        label.setStyle("-fx-border-color: black; -fx-alignment: center;");
        return label;
    }
    private Label createCellBar(int value) {
        Label label = new Label(String.valueOf(value));
        label.setFont(Font.font("Arial", 16));
        label.setTextFill(Color.GREEN);
        label.setMinSize(50, 50);
        label.setStyle("-fx-border-color: green; -fx-alignment: center;");
        return label;
    }
}
