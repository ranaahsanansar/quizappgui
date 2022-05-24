package ahsan.quizapplication.Models;

import java.sql.*;

public class CrateConnection {
     private static final String  url = "jdbc:mysql://localhost:3306/quizappdb";
     private static final String useName = "root";
     private static final String password = "";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url , useName , password);
        System.out.println("Connected");
        return connection;

    }
}
