package ahsan.quizapplication.Models;

import java.sql.*;

public class StudentModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private int rollNumber;
    private String studentEmail;

    private String studentPassword;

    public StudentModel(){

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


    public void insert(){
        Connection conn = null ;
        PreparedStatement preparedStatement = null;


        try{

            String querryRaw = "INSERT INTO `%s` (`%s`, `%s`, `%s`, `%s`, `%s`, `%s`) VALUES (NULL, ?, ?, ?, ?, ?)";
            String querry = String.format(querryRaw , MetaData.TABLE_NAME , MetaData.ID , MetaData.FIRST_NAME , MetaData.LAST_NAME , MetaData.ROLL_NUMBER , MetaData.USER_NAME , MetaData.PASSWORD);

            conn = CrateConnection.getConnection();
            preparedStatement = conn.prepareStatement(querry);
            preparedStatement.setString(1 , firstName);
            preparedStatement.setString(2 , lastName);
            preparedStatement.setInt(3 , rollNumber);
            preparedStatement.setString(4 , studentEmail);
            preparedStatement.setString(5 , studentPassword);
            System.out.println("Query: " + querry);
            int i = preparedStatement.executeUpdate();

            System.out.println("Inserted");
        }catch (SQLException e){
            System.out.println("SQL : " + e.getMessage());

        }catch (Exception e){
            System.out.println("Error " + e.getMessage());

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
