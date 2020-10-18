package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashController  implements Initializable {
    @FXML
    private AnchorPane splash;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Thread.sleep(3000);
//            checkUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkUser(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/dashboard.fxml"));
            Parent root = loader.load();
        Stage primaryStage = (Stage) splash.getScene().getWindow();
        primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
