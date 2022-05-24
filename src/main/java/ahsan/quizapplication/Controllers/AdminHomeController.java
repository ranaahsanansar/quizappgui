package ahsan.quizapplication.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import ahsan.quizapplication.Models.QuestionModel;
import ahsan.quizapplication.Models.QuizModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
//-------------------


public class AdminHomeController  implements Initializable {

    @FXML
    private TabPane adminTabPane;
    @FXML
    private Tab addQuizTab;
    @FXML
    private Tab addStudentTab;

//    Admin Crete QuizModel Tab

    @FXML
    private TextField quizTitle;
    @FXML
    private Button quizTitleOKbtn;
    @FXML
    private TextArea question;
    @FXML
    private TextField opt1;
    @FXML
    private TextField opt2;
    @FXML
    private TextField opt3;
    @FXML
    private TextField opt4;
    @FXML
    private RadioButton radioOpt1;
    @FXML
    private RadioButton radioOpt2;
    @FXML
    private RadioButton radioOpt3;
    @FXML
    private RadioButton radioOpt4;
    @FXML
    private Button addNewQuestion;
    @FXML
    private Button submitQuiz;


    private ToggleGroup radioButtons;
    QuizModel quiz = new QuizModel();

    private String title ;

    private HashMap<String , String[]> questionsRecord = new HashMap<>();
    private ArrayList<QuestionModel> questionArrayList = new ArrayList<>();


    private void radioButtons(){
        radioButtons = new ToggleGroup();
        radioOpt1.setToggleGroup(radioButtons);
        radioOpt2.setToggleGroup(radioButtons);
        radioOpt3.setToggleGroup(radioButtons);
        radioOpt4.setToggleGroup(radioButtons);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioButtons();
//        Saving Title Of the QuizModel


    } //end of Initializ

    public void quizTitleAction(ActionEvent actionEvent) {
        System.out.println("Handel");
        String title = quizTitle.getText().trim();
        if (title.trim().isEmpty()){
            Notifications notifications = Notifications.create();
            notifications.text("Please Enter a Valid Title");
            notifications.title("Invalid");

            notifications.position(Pos.TOP_RIGHT);
            notifications.hideAfter(Duration.millis(2000));

            notifications.showError();
//                    System.out.println("Empty Title");
        } else if (!title.isEmpty()) {
            Notifications notification = Notifications.create();
            notification.text("Title Saved now you can not change it")
                    .title("Saved")
                    .position(Pos.TOP_LEFT)
                    .hideAfter(Duration.seconds(2));
            notification.show();
            quizTitle.setEditable(false);
            quiz.setTitle(title) ;
            this.title = title;
//            System.out.println("Title : " + this.title);
            quizTitleOKbtn.setVisible(false);

        }
    }

    public void addNewQuestionAction(ActionEvent actionEvent) {
        String ques = question.getText();
        String op1 = opt1.getText();
        String op2 = opt2.getText();
        String op3 = opt3.getText();
        String op4 = opt4.getText();
        Toggle selectedRadioOp = radioButtons.getSelectedToggle();

        if (ques.trim().isEmpty()
                || op1.trim().isEmpty()
                || op2.trim().isEmpty()
                || op3.trim().isEmpty()
                || op4.trim().isEmpty()){

            Notifications notifications = Notifications.create().title("Question").text("All fields are required").position(Pos.TOP_RIGHT);
            notifications.showError();
        }else if (selectedRadioOp == null){
            Notifications notifications = Notifications.create().title("Option Error").text("Please select right answer").position(Pos.TOP_RIGHT);
            notifications.showError();
        }else if (this.title == null) {
            Notifications notifications = Notifications.create().title("Title Empty").text("Please set a title").position(Pos.TOP_RIGHT);
            notifications.showError();
        }else {

            try {

                QuestionModel questionIndex = new QuestionModel();
                questionIndex.setQuestion(ques);
                questionIndex.setOpt1(op1);
                questionIndex.setOpt2(op2);
                questionIndex.setOpt3(op3);
                questionIndex.setOpt4(op4);

                if(selectedRadioOp == radioOpt1){
                    questionIndex.setAnswer(op1);
                } else if (selectedRadioOp == radioOpt2) {
                    questionIndex.setAnswer(op2);
                }else if (selectedRadioOp == radioOpt3) {
                    questionIndex.setAnswer(op3);
                }else if (selectedRadioOp == radioOpt4) {
                    questionIndex.setAnswer(op4);
                }

                questionArrayList.add(questionIndex);

                System.out.println(quiz);

                for (QuestionModel s : questionArrayList){
                    System.out.println(s);
                }



//                String[] data = new String[5];

//                data[0] = op1;
//                data[1] = op2;
//                data[2] = op3;
//                data[3] = op4;
//                if(selectedRadioOp == radioOpt1){
//                    data[4] = op1;
//                } else if (selectedRadioOp == radioOpt2) {
//                    data[4] = op2;
//                }else if (selectedRadioOp == radioOpt3) {
//                    data[4] = op3;
//                }else if (selectedRadioOp == radioOpt4) {
//                    data[4] = op4;
//                }

//                questionsRecord.put(question.getText().trim() , data);
//
////                System.out.println("Question" + questionsRecord.keySet());
//
//                Set<String> keySet = questionsRecord.keySet();
//                Iterator<String> itr = keySet.iterator();
//
//                while (itr.hasNext()){
//                    String qu = itr.next();
//                    String[] values = questionsRecord.get(qu);
//
//                    System.out.println("Question: " + qu);
//                    for(String s: values){
//                        System.out.println(s);
//                    }
//                }


                question.clear();
                opt1.clear();
                opt2.clear();
                opt3.clear();
                opt4.clear();


            }catch (IndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }catch (Exception e){
                System.out.println("Exception: "+e.getMessage());
            }

        }
    }

    public void submitQuizAction(ActionEvent actionEvent) {
        quiz.insertQuiz();
    }


//    ------------------


}
