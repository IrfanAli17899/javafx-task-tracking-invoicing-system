package app.utils;

import app.DB.ConnectionClass;
import app.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public final class UtilsClass {
    private UtilsClass(){};

    public static ResultSet executeQuery(String query) {
        ConnectionClass connection = new ConnectionClass();
        Connection connectDB = connection.getConnection();
        try {
            Statement st = connectDB.createStatement();
            return st.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void navigate(Node node, URL path) throws IOException {
        FXMLLoader loader = new FXMLLoader(path);
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}
