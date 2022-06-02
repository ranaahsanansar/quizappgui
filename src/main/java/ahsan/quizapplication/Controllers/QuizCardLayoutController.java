package ahsan.quizapplication.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class QuizCardLayoutController implements Initializable {
    public Label quizTitle;
    public Label noQ;
    public Button attemptQ;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setNoQ(String value) {
        this.noQ.setText(value);
    }

    public void setQuizTitle(String value) {
        this.quizTitle.setText(value);
    }

    public void setAttemptQ(Button attemptQ) {
        this.attemptQ = attemptQ;
    }



    @FXML
    private void attemptQuiz(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuizView.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }


    }

}
