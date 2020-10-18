package app.controllers;

import app.model.User;
import app.utils.LocalStorage;
import app.utils.UtilsClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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
    public void signin(ActionEvent event) {
        try {
            errorMessage.setText("");
            ResultSet rs = UtilsClass.executeQuery(String.format("SELECT * FROM users WHERE email='%s' and password='%s'", EmailInput.getText(), PasswordInput.getText()));
            if(rs.next()){
                LocalStorage.getInstance().setUser(new User(rs.getInt("id"), rs.getString("username")));
                UtilsClass.navigate(SigninButton,getClass().getResource("../fxml/dashboard.fxml"));
            }
            errorMessage.setText("Please Provide Valid Credentials");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
