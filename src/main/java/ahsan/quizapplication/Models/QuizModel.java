package ahsan.quizapplication.Models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class QuizModel {


    private Integer quizId;
    private String title;

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

        String query = "CREATE TABLE IF NOT EXISTS `quizzes` ( `id` INT(50) NOT NULL AUTO_INCREMENT , `title` VARCHAR(50) NOT NULL , PRIMARY KEY (`id`))";
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
