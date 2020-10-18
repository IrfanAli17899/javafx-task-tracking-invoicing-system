package app.model;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class Task {
    String title;
    String hrs;
    Button action;
    CheckBox check;

    public Task(String title, String hrs, Button action, CheckBox check){
        this.title = title;
        this.hrs = hrs;
        this.action = action;
        this.check = check;
    }
    public String getTitle() {
        return title;
    }
    public String getHrs() {
        return hrs;
    }
    public Button getAction() {
        return action;
    }
    public CheckBox getCheck() {
        return check;
    }

    public void setAction(Button action) {
        this.action = action;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }

    public void setHrs(String hrs) {
        this.hrs = hrs;
    }

}
