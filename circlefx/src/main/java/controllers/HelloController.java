package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private Stage stage;
    private Scene scene;

    // Ortak bir yöntem ile sahneleri değiştir
    private void switchScene(ActionEvent event, String fxmlFileName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void yoneticipanel(ActionEvent event) throws IOException {
        switchScene(event, "yonetici.fxml");
    }

    @FXML
    public void kayitpanel(ActionEvent event) throws IOException {
        switchScene(event, "kayit.fxml");
    }

    @FXML
    public void girispanel(ActionEvent event) throws IOException {
        switchScene(event, "giris.fxml");
    }

}
