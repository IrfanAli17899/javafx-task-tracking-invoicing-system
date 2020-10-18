package app.controllers;

import app.model.Task;
import app.utils.LocalStorage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TrackingController implements Initializable {
    @FXML
    private TextField taskInput;
    @FXML
    private TableView<Task> task_table;

    @FXML
    private TableColumn<Task, CheckBox> col_check;

    @FXML
    private TableColumn<Task,String> col_title;

    @FXML
    private TableColumn<Task,String> col_hrs;

    @FXML
    private TableColumn<Task, Button> col_action;

    ObservableList<Task> tasks =  FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTable();
        loadTable();
//        setRow("Test");
    }

    @FXML
    private void addTask(){
        String title = taskInput.getText();
        if (title.length()>0){
            this.setRow(title);
            taskInput.setText("");
        }
    }

    private void loadTable(){
        ArrayList<Task> tasksFetched = LocalStorage.getInstance().getAllTasks();
        for (Task t: tasksFetched) {
            t.setAction(createButton());
            t.setCheck(new CheckBox());
            tasks.add(t);
        }
        task_table.setItems(tasks);
    };

    private void handlePlay(){

    }

    private void createTable(){
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_hrs.setCellValueFactory(new PropertyValueFactory<>("hrs"));
        col_action.setCellValueFactory(new PropertyValueFactory<>("action"));
        col_check.setCellValueFactory(new PropertyValueFactory<>("check"));
    }

    private Button createButton(){
        Button btn = new Button("");
        FontAwesomeIcon icon = new FontAwesomeIcon();
        icon.setIcon(FontAwesomeIcons.PLAY);
        btn.setGraphic(icon);
        btn.setOnAction((event)->this.handlePlay());
        return btn;
    }

    private void setRow(String title){
        tasks.add(new Task(title,"00:00:00", createButton(),new CheckBox()));
        task_table.setItems(tasks);
    }
}
