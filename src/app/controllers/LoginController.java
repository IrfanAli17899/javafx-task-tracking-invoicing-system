package app.controllers;

import app.utils.UtilsClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class LoginController implements Initializable {
    @FXML
    private Button SigninButton;

    @FXML
    private TextField EmailInput;

    @FXML
    private TextField PasswordInput;

    @FXML
    private Text errorMessage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void signin(ActionEvent event) throws IOException {
        errorMessage.setText("");
        String email = EmailInput.getText();
        String password = PasswordInput.getText();
        UtilsClass.navigate(SigninButton,getClass().getResource("../fxml/dashboard.fxml"));
//        UtilsClass.executeQuery("SELECT * FROM users WHERE email='email' and password='password'");
    }

}
