package app.utils;

import app.model.Task;
import app.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public final class LocalStorage {
    private User user;
    private ObservableList<Task> tasks =  FXCollections.observableArrayList();
    private TimerApp timer = new TimerApp();
    private Task crrTask = null;
    private String previousTime;
    private final static LocalStorage INSTANCE = new LocalStorage();

    private LocalStorage(){
    }

    public static LocalStorage getInstance() {
        return INSTANCE;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public User getUser() {
        return this.user;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void updateTask(Task t) {
        tasks.set(tasks.indexOf(t),t);
    }

    public ObservableList<Task> getAllTasks() {
        return this.tasks;
    }

    public Task getCrrTask() {
        return crrTask;
    }

    public void setCrrTask(Task crrTask) {
        this.crrTask = crrTask;
    }

    public void stopTimer(){
        timer.stop();
    }

    public void startTimer(long sec){
        this.stopTimer();
        this.timer = new TimerApp();
        timer.setSec(sec);
        timer.start();
    }

    public void setTime(String hrs){
        crrTask.setHrs(hrs);
        this.updateTask(crrTask);
    }

    public void setPreviousTime(String t){
        this.previousTime = t;
    }

    public String getPreviousTime() {
        return previousTime;
    }
}
