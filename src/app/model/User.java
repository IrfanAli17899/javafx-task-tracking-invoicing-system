package app.model;

public class User {
    int id;
    String username;
    public User(int id, String username){
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }
}
