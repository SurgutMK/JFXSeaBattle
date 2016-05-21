package ru.kokorin.jfxseabattle.controllers;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import ru.kokorin.jfxseabattle.models.Player;


import java.io.IOException;


public class MainController {
    @FXML
    private TextField editName;
    @FXML
    private Label labelName;
    private static Player player;

    public void btnExitClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnStartClick(ActionEvent actionEvent) {
        if (editName.getText().equals("")) {
            labelName.setId("labelNameError");
            animation(labelName);
        } else {
            try {
                player = new Player(editName.getText());
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../views/game.fxml"));
                stage.setTitle("JFXSeaBattle");
                stage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root, Color.TRANSPARENT);
                scene.getStylesheets().add(0, "ru/kokorin/jfxseabattle/css/style.css");
                stage.setScene(scene);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Player getPlayer() {
        return player;
    }

    private void animation(Node node) {
        ScaleTransition st = new ScaleTransition(Duration.seconds(0.2), node);
        st.setToX(1.2);
        st.setToY(1.2);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();
    }
}
