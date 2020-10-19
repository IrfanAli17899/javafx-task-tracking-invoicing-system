package app.controllers;

import app.model.Task;
import app.utils.LocalStorage;
import app.utils.UtilsClass;
import com.sun.javafx.scene.control.skin.Utils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    ObservableList<Task> tasks =  LocalStorage.getInstance().getAllTasks();

    int userId = LocalStorage.getInstance().getUser().getId();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTable();
        loadTable();
    }

    @FXML
    private void addTask(){
        try{
        String title = taskInput.getText();
        if (title.length()>0){
            ResultSet rs = UtilsClass.executeDB(String.format("INSERT INTO `tasks`(`title`, `hrs`, `invoiced`, `user_id`) VALUES ('%s','00:00:00',0,%s)",title,userId),true);
            System.out.println("rialskjdklasjdklj");
            System.out.println(rs.getObject(1));
            Task task = new Task(rs.getInt("id"), title,"00:00:00", null, createCheckbox());
            task.setAction(createButton(task));
            LocalStorage.getInstance().addTask(task);
            task_table.setItems(tasks);
            taskInput.setText("");
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadTable(){
        for (Task t: tasks) {
            t.setAction(createButton(t));
            t.setCheck(new CheckBox());
        }
        task_table.setItems(tasks);
    };

    private void handlePlay(Task task){
        System.out.println("running here.......");
        FontAwesomeIcon icon = new FontAwesomeIcon();
        LocalStorage localStorage = LocalStorage.getInstance();
            Task crrTask = localStorage.getCrrTask();
            if (crrTask!=null){
                icon.setIcon(FontAwesomeIcons.PLAY);
                crrTask.getAction().setGraphic(icon);
                localStorage.updateTask(crrTask);
                UtilsClass.executeDB(String.format("UPDATE tasks SET hrs='%s' WHERE id=%s",crrTask.getHrs(),crrTask.getId()),true);
                String timeDiff =UtilsClass.calculateTimeDiff(localStorage.getPreviousTime(),crrTask.getHrs());
                UtilsClass.executeDB(String.format("INSERT INTO times (user_id,task_id,hrs) VALUES (%s,%s,'%s')",
                        localStorage.getUser().getId(),crrTask.getId(),timeDiff),true);
                if (crrTask.getId()==task.getId()){
                    localStorage.stopTimer();
                    localStorage.setCrrTask(null);
                    localStorage.setPreviousTime("");
                    return;
                }
            }
            localStorage.setCrrTask(task);
            localStorage.setPreviousTime(task.getHrs());
            icon.setIcon(FontAwesomeIcons.STOP);
            task.getAction().setGraphic(icon);
            localStorage.updateTask(task);
            long time = UtilsClass.timeToSec(task.getHrs());
            localStorage.startTimer(time);
    }

    private void createTable(){
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_hrs.setCellValueFactory(new PropertyValueFactory<>("hrs"));
        col_action.setCellValueFactory(new PropertyValueFactory<>("action"));
        col_check.setCellValueFactory(new PropertyValueFactory<>("check"));
    }

    private Button createButton(Task task){
        Button btn = new Button("");
        FontAwesomeIcon icon = new FontAwesomeIcon();
        icon.setIcon(FontAwesomeIcons.PLAY);
        btn.setGraphic(icon);
        btn.setOnAction((event)-> handlePlay(task));
        return btn;
    }

    private CheckBox createCheckbox(){
        CheckBox checkBox = new CheckBox("");
        checkBox.setOnAction(e->{
            System.out.println("Working");
            List<Task> t = tasks.stream().filter(task -> task.getCheck().isSelected()==true).collect(Collectors.toList());
            if (t.size()>0){
                System.out.println("Button Show");
            }else {
                System.out.println("Button Hide");
            }
        });
        return checkBox;
    }


}
