package ahsan.quizapplication.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.sql.*;
import java.util.ArrayList;

public class StudentModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer rollNumber;
    private String studentEmail;
    public String studentPassword;

    public StudentModel(){

    }

    public StudentModel(String userName , String password){
        this.studentEmail = userName;
        this.studentPassword = password;
    }
    public StudentModel(String firstName, String lastName, int rollNumber, String studentEmail, String studentPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNumber = rollNumber;
        this.studentEmail = studentEmail;
        this.studentPassword = studentPassword;

    }

    public static class MetaData{
        public static String TABLE_NAME = "students";
        public static String ID = "id";
        public static String FIRST_NAME = "first_name" ;
        public static String LAST_NAME = "last_name" ;
        public static String ROLL_NUMBER = "roll_number" ;
        public static String USER_NAME = "user_name";
        public static String PASSWORD = "password";

    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public static void crateTable(){
        Connection conn = null;
        Statement statement = null;

        String queryRaw = "CREATE TABLE IF NOT EXISTS `%s` ( `%s` INT(50) NOT NULL AUTO_INCREMENT , `%s` VARCHAR(50) NOT NULL, `%s` VARCHAR(50) NOT NULL, `%s` INT(50) NOT NULL, `%s` VARCHAR(50) NOT NULL, `%s` VARCHAR(50) NOT NULL , UNIQUE(`%s`) , UNIQUE(`%s`) ,PRIMARY KEY (`%s`))";
        String query = String.format(queryRaw , MetaData.TABLE_NAME , MetaData.ID , MetaData.FIRST_NAME , MetaData.LAST_NAME , MetaData.ROLL_NUMBER, MetaData.USER_NAME , MetaData.PASSWORD , MetaData.USER_NAME  , MetaData.ROLL_NUMBER , MetaData.ID );

        try {
            conn = CrateConnection.getConnection();
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Student Table!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static ArrayList<StudentModel> getAll(){
        ArrayList<StudentModel> studentsGetFromDataBase = new ArrayList<>();
        try{
            Connection conn = null ;
            Statement statement = null;
            ResultSet resultSet = null;

            conn = CrateConnection.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `students`");

            while (resultSet.next()){
                StudentModel s = new StudentModel();
                s.setFirstName(resultSet.getString(MetaData.FIRST_NAME)) ;
                s.setLastName(resultSet.getString(MetaData.LAST_NAME));
                s.setRollNumber(resultSet.getInt(MetaData.ROLL_NUMBER));
                s.setStudentEmail(resultSet.getString(MetaData.USER_NAME));
                s.setStudentPassword(resultSet.getString(MetaData.PASSWORD));

                studentsGetFromDataBase.add(s);
                System.out.println(s);
            }

        }catch (SQLException e){
            Notifications notifications = Notifications.create().title("Error").text(e.getMessage()).position(Pos.TOP_RIGHT);
            notifications.showError();
        }catch (Exception e){
            Notifications notifications = Notifications.create().title("Error").text(e.getMessage()).position(Pos.TOP_RIGHT);
            notifications.showError();
        }
        return studentsGetFromDataBase;
    }

    public void insert(){
        Connection conn = null ;
        PreparedStatement preparedStatement = null;


        try{

            String querryRaw = "INSERT INTO `%s` (`%s`, `%s`, `%s`, `%s`, `%s`, `%s`) VALUES (NULL, ?, ?, ?, ?, ?)";
            String querry = String.format(querryRaw , MetaData.TABLE_NAME , MetaData.ID , MetaData.FIRST_NAME , MetaData.LAST_NAME , MetaData.ROLL_NUMBER , MetaData.USER_NAME , MetaData.PASSWORD);

            conn = CrateConnection.getConnection();
            preparedStatement = conn.prepareStatement(querry , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1 , firstName);
            preparedStatement.setString(2 , lastName);
            preparedStatement.setInt(3 , rollNumber);
            preparedStatement.setString(4 , studentEmail);
            preparedStatement.setString(5 , studentPassword);
            System.out.println("Query: " + querry);
            int i = preparedStatement.executeUpdate();

            ResultSet key = preparedStatement.getGeneratedKeys();
            if(key.next()) {
                this.id = key.getInt(1);
            }

            Notifications notifications = Notifications.create().title("Success").text("Student Registered!").position(Pos.TOP_RIGHT);
            notifications.show();
        }catch (SQLException e){
            System.out.println("SQL : " + e.getMessage());
            Notifications notifications = Notifications.create().title("Failed").text(e.getMessage()).position(Pos.TOP_RIGHT);
            notifications.showError();

        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
            Notifications notifications = Notifications.create().title("Success").text("Error /n" + e.getMessage()).position(Pos.TOP_RIGHT);
            notifications.showError();

        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public boolean login(){
        Connection conn = null ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{

            String querryRaw = "SELECT * FROM `students` WHERE `user_name` LIKE ? AND `password` LIKE ?";
            String querry = String.format(querryRaw , MetaData.TABLE_NAME , MetaData.USER_NAME , MetaData.PASSWORD);

            conn = CrateConnection.getConnection();
            preparedStatement = conn.prepareStatement(querry , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1 , this.studentEmail);
            preparedStatement.setString(2 , this.studentPassword);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                this.setFirstName(resultSet.getString("first_name"));
                this.setLastName(resultSet.getString("last_name"));
                this.setRollNumber(resultSet.getInt("roll_number"));
                this.setId(resultSet.getInt("id"));
                Notifications notifications = Notifications.create().title("Success").text("Login as Student").position(Pos.TOP_RIGHT);
                notifications.show();
                return true;
            }else {
                Notifications notifications = Notifications.create().title("Failed").text("Incorrect field").position(Pos.TOP_RIGHT);
                notifications.showError();
                return false;
            }


        }catch (SQLException e){
            System.out.println("SQL : " + e.getMessage());
            Notifications notifications = Notifications.create().title("Failed").text(e.getMessage()).position(Pos.TOP_RIGHT);
            notifications.showError();
            return false;

        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
            Notifications notifications = Notifications.create().title("Success").text("Error /n" + e.getMessage()).position(Pos.TOP_RIGHT);
            notifications.showError();
            return false;
        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
