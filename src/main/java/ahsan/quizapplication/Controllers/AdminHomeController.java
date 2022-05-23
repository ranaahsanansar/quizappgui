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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        quizTitleOKbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Handel");
            }
        });
    }




//    ------------------


}
