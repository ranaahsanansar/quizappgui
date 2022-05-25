package ahsan.quizapplication.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import ahsan.quizapplication.Models.QuestionModel;
import ahsan.quizapplication.Models.QuizModel;
import ahsan.quizapplication.Models.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
//-------------------


public class AdminHomeController  implements Initializable {

    public TextField firstName;
    public TextField secondName;
    public TextField rollNumber;
    public Button addStudentbtn;
    public TableColumn rollNumberColumn;
    public TableColumn firstNameColumn;
    public TableColumn lastNameColumn;
    public TextField studentEmail;
    public TextField studentPassword;
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
        submitQuiz.setVisible(false);


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
        addQuestion();
    }

    public void addQuestion(){
        submitQuiz.setVisible(true);
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
                questionIndex.setQuiz(quiz);

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

//                for (QuestionModel s : questionArrayList){
//                    System.out.println(s);
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
        addQuestion();
        quiz.save(questionArrayList);
        quizTitle.clear();
        quizTitleOKbtn.setVisible(true);
        submitQuiz.setVisible(false);

    }

//    ----------------------------------------------------------------
//-------------------------------------------------------------------


    public void addStudentAction(ActionEvent actionEvent) {

        try {
            String firstName = this.firstName.getText().trim();
            String lastName = this.secondName.getText().trim();
            String rollNumber = this.rollNumber.getText().trim();
            String studentEmail = this.studentEmail.getText().trim();
            String studentPassword = this.studentPassword.getText().trim();

            String message = null;
            if(firstName.length()<3){
                message = "Invalid First Name";
            }else if(lastName.length()<3){
                message = "Invalid Last Name";
            }else if(rollNumber.isEmpty() || firstName.isEmpty()
                    || lastName.isEmpty() || studentEmail.isEmpty() || studentPassword.isEmpty()){
                message = "All fields are Required";
            }
            int rollNumberInt;

            rollNumberInt = Integer.parseInt(rollNumber);

            if (message != null){
                throw new RuntimeException(message);
            }

            StudentModel student = new StudentModel(firstName , lastName , rollNumberInt , studentEmail , studentPassword);
            student.insert();

            this.firstName.clear();
            this.secondName.clear();
            this.studentEmail.clear();
            this.studentPassword.clear();
            this.rollNumber.clear();

        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
            Notifications notifications = Notifications.create().title("Error").text(e.getMessage()).position(Pos.TOP_RIGHT);
            notifications.showError();

        }catch (Exception e){
            Notifications notifications = Notifications.create().title("Invalid").text("All fields must be Valid").position(Pos.TOP_RIGHT);
            notifications.showWarning();
            notifications = Notifications.create().title("Invalid").text(e.getMessage()).position(Pos.TOP_RIGHT);
            notifications.showWarning();
        }


    }


//    ------------------


}
