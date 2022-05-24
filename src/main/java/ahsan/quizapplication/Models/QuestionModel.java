package ahsan.quizapplication.Models;

import java.sql.Connection;
import java.sql.Statement;

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
        public static final String OPT1 = "";
        public static final String OPT2 = "";
        public static final String OPT3 = "";
        public static final String OPT4 = "";
        public static final String ANSWER = "";

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
        Connection conn ;
        Statement statement;

        String query = "CREATE TABLE IF NOT EXISTS `questions` ( `id` INT(50) NOT NULL AUTO_INCREMENT , `question` VARCHAR (1000) , `opt1` VARCHAR (500), `opt2` VARCHAR (500), `opt3` VARCHAR (500), `opt4` VARCHAR (500), `answer` VARCHAR (500), quiz_id INTEGER, FOREIGN KEY (quiz_id) REFERENCES quizzes(id) , PRIMARY KEY (`id`)) ;";
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
