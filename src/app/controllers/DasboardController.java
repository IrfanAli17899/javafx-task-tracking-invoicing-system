package app.controllers;

import app.model.Task;
import app.model.User;
import app.utils.LocalStorage;
import app.utils.UtilsClass;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DasboardController implements Initializable {

    @FXML
    private AnchorPane SidebarNavigation;
    @FXML
    private VBox main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         loadpage("tracking");
    }

    private void loader(){
        ImageView image = new ImageView("loader.gif");
        fade(image.getParent());
        main.getChildren().add(image);
    }

    @FXML
    private void handleNavigation(ActionEvent event){
        System.out.println("Menu Clicked");
        Button crrBtn = ((Button) event.getTarget());
        toggleActiveButtons(crrBtn);
        loadpage(crrBtn.getId());
    }

    private void toggleActiveButtons(Button crrBtn){
        SidebarNavigation.getChildren().forEach((node -> node.getStyleClass().remove("sidebar_active_button")));
        crrBtn.getStyleClass().add("sidebar_active_button");
    }

    private void fade(Parent node){
        FadeTransition transition = new FadeTransition(Duration.millis(1000), node);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }

    private void getPageData(String page){
        try{
        LocalStorage localStorage = LocalStorage.getInstance();
        User user = localStorage.getUser();
        switch (page){
            case "tracking":
                if (localStorage.getAllTasks().size()==0){
                    ResultSet rs = UtilsClass.executeDB(String.format("SELECT * FROM tasks WHERE user_id=%s AND invoiced=0", user.getId()),false);
                    while (rs.next()){
                        localStorage.addTask(new Task(rs.getInt("id"),rs.getString("title"),rs.getString("hrs"),null,null));
                    }
                }
        }
        }catch (Exception e){}
    }


    private void loadpage(String page) {
        try {
            loader();
            getPageData(page);
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/"+page+".fxml"));
            root = loader.load();
            main.getChildren().clear();
            fade(root);
            main.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
