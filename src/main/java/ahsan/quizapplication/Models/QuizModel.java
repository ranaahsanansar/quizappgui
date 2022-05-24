package ahsan.quizapplication.Models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class QuizModel {


    private Integer quizId;
    private String title;

    public static class MetaData{
        public static String TABLE_NAME = "quizzes";
        public static String QUIZ_ID = "id";
        public static String QUIZ_TITLE = "title";
    }

    public QuizModel(){

    }
    public QuizModel(String title) {
        this.title = title;
    }


    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    ----------------------
    public static void crateTable(){
        Connection conn ;
        Statement statement;

        String queryRaw = "CREATE TABLE IF NOT EXISTS `%s` ( `%s` INT(50) NOT NULL AUTO_INCREMENT , `%s` VARCHAR(50) NOT NULL , PRIMARY KEY (`%s`))";
        String query = String.format(queryRaw , MetaData.TABLE_NAME , MetaData.QUIZ_ID , MetaData.QUIZ_TITLE , MetaData.QUIZ_ID );
//        System.out.println("Quiz Model: " + query);
        try {
            conn = CrateConnection.getConnection();
            statement = conn.createStatement();
            statement.execute(query);
//            boolean info = statement.execute(query);
//            System.out.println("Table Created" + info);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
