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

public class SignupController implements Initializable {
    @FXML
    private Button SignupButton;

    @FXML
    private TextField Username;

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
    public void signup(ActionEvent event) {
        try {
            errorMessage.setText("");
            ResultSet rs = UtilsClass.executeDB(String.format("INSERT INTO users (email,username,password) VALUES ('%s','%s','%s')", EmailInput.getText(), Username.getText(), PasswordInput.getText()),true);
            if(rs.next()){
                LocalStorage.getInstance().setUser(new User(rs.getInt("id"), rs.getString("username")));
                UtilsClass.navigate(SignupButton,getClass().getResource("../fxml/dashboard.fxml"));
            }
            errorMessage.setText("Please Provide Valid Credentials");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void gosignin(ActionEvent event) {
        try{
        UtilsClass.navigate(SignupButton,getClass().getResource("../fxml/login.fxml"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
