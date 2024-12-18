module com.phase3.coinsgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.phase3.coinsgame to javafx.controls,javafx.fxml;
    exports com.phase3.coinsgame;
}