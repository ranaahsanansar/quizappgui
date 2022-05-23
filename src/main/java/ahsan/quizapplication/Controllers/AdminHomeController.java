package ahsan.quizapplication.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import ahsan.quizapplication.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

//    Admin Crete Quiz Tab

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
//        Saving Title Of the Quiz
        quizTitleOKbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Handel");
                String title = quizTitle.getText();
                if (title.trim().isEmpty()){
                    Notifications notifications = Notifications.create();
                    notifications.text("Please Enter a Valid Title");
                    notifications.title("Invalid");

                    notifications.position(Pos.TOP_RIGHT);
                    notifications.hideAfter(Duration.millis(2000));

                    notifications.showError();
//                    System.out.println("Empty Title");
                }else {
                    quizTitle.setEditable(false);
                }
            }
        });

        addNewQuestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
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
                }else {
                    
                }
            }
        });


    } //end of Initializ




//    ------------------


}
