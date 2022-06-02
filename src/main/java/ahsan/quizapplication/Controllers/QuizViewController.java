package ahsan.quizapplication.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class QuizViewController implements Initializable {


    public Label quizTitle;
    public Label time;
    public Label quizQuestion;
    public RadioButton op1;
    public ToggleGroup QuizOptions;
    public RadioButton op2;
    public RadioButton op3;
    public RadioButton op4;
    public Button nextbtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void nextQuestion(ActionEvent actionEvent) {
    }
}
