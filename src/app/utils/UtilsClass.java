package app.utils;

import app.DB.ConnectionClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public final class UtilsClass {
    private UtilsClass(){

    };

    public static ResultSet executeDB(String query, Boolean update) {
        try {
            ConnectionClass connection = new ConnectionClass();
            Connection connectDB = connection.getConnection();
            Statement st = connectDB.createStatement();
            if (update){
                st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                return st.getGeneratedKeys();
            }else {

                return st.executeQuery(query);
            }
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

    public static long timeToSec(String time){
        String[] t = time.split(":");
        return TimeUnit.HOURS.toSeconds(Integer.parseInt(t[0])) +
                TimeUnit.MINUTES.toSeconds(Integer.parseInt(t[1])) + TimeUnit.SECONDS.toSeconds(Integer.parseInt(t[2]));
    }

    public static String getTime(long sec){
        DecimalFormat df = new DecimalFormat("00");
        return df.format(sec / 3600) +":" + df.format((sec % 3600) / 60) +":"+  df.format(sec % 60) ;
    }

    public static String calculateTimeDiff(String t1,String t2){
        return UtilsClass.getTime(Math.abs(UtilsClass.timeToSec(t1) - UtilsClass.timeToSec(t2)));
    }

}
