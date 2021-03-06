package ahsan.quizapplication.Models;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class QuestionModel {

    private QuizModel quiz;
    private Integer questionId;
    private String question;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private String answer;

    public static class MetaData {
        public static final String TABLE_NAME = "questions";
        public static final String QUESTION = "question";
        public static final String OPT1 = "opt1";
        public static final String OPT2 = "opt2";
        public static final String OPT3 = "opt3";
        public static final String OPT4 = "opt4";
        public static final String ANSWER = "answer";
        public static final String QUIZ_ID = "quiz_id";

    }

    public  QuestionModel(){

    }

    public QuestionModel(QuizModel quiz, String question, String opt1, String opt2, String opt3, String opt4, String answer) {
        this.quiz = quiz;
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.answer = answer;
    }

    public void setQuiz(QuizModel quiz) {
        this.quiz = quiz;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public QuizModel getQuiz() {
        return quiz;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getOpt1() {
        return opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public String getAnswer() {
        return answer;
    }

    public static void crateTable(){
        Connection conn = null ;
        Statement statement = null;

        String queryRaw = "CREATE TABLE IF NOT EXISTS `%s` ( `id` INT(50) NOT NULL AUTO_INCREMENT , `%s` VARCHAR (1000) , `%s` VARCHAR (500), `%s` VARCHAR (500), `%s` VARCHAR (500), `%s` VARCHAR (500), `%s` VARCHAR (500), %s INTEGER, FOREIGN KEY (%s) REFERENCES %s(%s) , PRIMARY KEY (`id`))";

        String query = String.format(queryRaw , MetaData.TABLE_NAME , MetaData.QUESTION , MetaData.OPT1 , MetaData.OPT2 , MetaData.OPT3 , MetaData.OPT4 , MetaData.ANSWER , MetaData.QUIZ_ID , MetaData.QUIZ_ID , QuizModel.MetaData.TABLE_NAME , QuizModel.MetaData.QUIZ_ID );

//        System.out.println(query);
        try {
            conn = CrateConnection.getConnection();
            statement = conn.createStatement();
            statement.execute(query);
//            boolean info = statement.execute(query);
//            System.out.println("Table Created" + info);

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


    public boolean insert(){

        Connection conn = null ;
        PreparedStatement preparedStatement = null;


        try{

            String querryRaw = "INSERT INTO `%s` (`id`, `%s`, `%s`, `%s`, `%s`, `%s`, `%s`, `%s`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);";
            String querry = String.format(querryRaw , MetaData.TABLE_NAME , MetaData.QUESTION , MetaData.OPT1 , MetaData.OPT2 , MetaData.OPT3, MetaData.OPT4 ,MetaData.ANSWER , MetaData.QUIZ_ID );

            conn = CrateConnection.getConnection();
            preparedStatement = conn.prepareStatement(querry);
            preparedStatement.setString(1 , this.question);
            preparedStatement.setString(2 , this.opt1);
            preparedStatement.setString(3 , this.opt2);
            preparedStatement.setString(4 , this.opt3);
            preparedStatement.setString(5 , this.opt4);
            preparedStatement.setString(6 , this.answer);
            preparedStatement.setInt(7 , this.quiz.getQuizId());

            System.out.println("Query: " + querry);
            preparedStatement.executeUpdate();

            System.out.println("Inserted");
            return true;
        }catch (SQLException e){
            System.out.println("SQL : " + e.getMessage());
            return false;
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
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
