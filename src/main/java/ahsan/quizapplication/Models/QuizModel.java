package ahsan.quizapplication.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    ----------------------
    public static void crateTable(){
        Connection conn = null;
        Statement statement = null;

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

    public int insert(){
        Connection conn = null ;
        PreparedStatement preparedStatement = null;


        try{

            String querryRaw = "INSERT INTO `%s` ( `%s`) VALUES ( \"%s\")";
            String querry = String.format(querryRaw , MetaData.TABLE_NAME, MetaData.QUIZ_TITLE , this.title);

            conn = CrateConnection.getConnection();
            preparedStatement = conn.prepareStatement(querry , Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1 , title);
            System.out.println("Query: " + querry);
            int i = preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next()){
                return keys.getInt(1);
            }
            System.out.println("Inserted");
        }catch (SQLException e){
            System.out.println("SQL : " + e.getMessage());
            return -1;
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
            return  -1;
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
        return -1;
    }

    public boolean save(ArrayList<QuestionModel> questions){
        boolean saved = true;
        this.quizId = this.insert();

        for(QuestionModel q : questions){

            saved = saved && q.insert();
        }

        return saved;
    }

    public static Map<QuizModel , Integer> questionCount(){
        Map<QuizModel, Integer> quizes  = new HashMap<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null ;
        ResultSet resultSet = null ;
        try{


            String queryRaw = "SELECT %s , %s.%s , COUNT(*) AS questios_count FROM %s INNER JOIN %s ON %s.%s = %s.%s GROUP BY %s.%s";
            String query = String.format(queryRaw , QuizModel.MetaData.QUIZ_TITLE , QuizModel.MetaData.TABLE_NAME , QuizModel.MetaData.QUIZ_ID , QuizModel.MetaData.TABLE_NAME , QuestionModel.MetaData.TABLE_NAME , QuizModel.MetaData.TABLE_NAME , QuizModel.MetaData.QUIZ_ID , QuestionModel.MetaData.TABLE_NAME , QuestionModel.MetaData.QUIZ_ID, QuizModel.MetaData.TABLE_NAME , QuizModel.MetaData.QUIZ_ID);

            conn = CrateConnection.getConnection();
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("querry Question Count: " + query);
            while (resultSet.next()){
                QuizModel temp = new QuizModel();
                temp.setQuizId(resultSet.getInt(QuizModel.MetaData.QUIZ_ID));
                temp.setTitle(resultSet.getString(QuizModel.MetaData.QUIZ_TITLE));
                int count = resultSet.getInt(3);
                quizes.put(temp , count);

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return quizes;
    }

}
