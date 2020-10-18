package app.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    public Connection databaseLink;
    public Connection getConnection(){
        String dbName = "demo";
        String dbUser = "root";
        String dbPassword = "";
        String url = "jdbc:mysql://localhost/" + dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,dbUser,dbPassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
