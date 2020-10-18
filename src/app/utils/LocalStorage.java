package app.utils;

import app.model.Task;
import app.model.User;

import java.util.ArrayList;

public final class LocalStorage {
    private User user;
    private ArrayList<Task> tasks = new ArrayList<>();
    private final static LocalStorage INSTANCE = new LocalStorage();

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

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }
}
