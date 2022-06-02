package ahsan.quizapplication.Controllers;

import ahsan.quizapplication.HelloApplication;
import ahsan.quizapplication.Models.QuestionModel;
import ahsan.quizapplication.Models.QuizModel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class StudetnHomeController implements Initializable {
    public Button backbtn;
    public StackPane stackPane;
    public FlowPane flowPane;

    Map<QuizModel , Integer>  quizcounts = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            quizcounts  = QuizModel.questionCount();
            Set<QuizModel> keys = quizcounts.keySet();
            System.out.println(keys);

            for(QuizModel quiz: keys){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuizCardLayout.fxml"));
                    Node node = fxmlLoader.load();
                    QuizCardLayoutController card = fxmlLoader.getController();
                    card.setQuizTitle(quiz.getTitle());
                    card.setNoQ(quizcounts.get(quiz) + " Questions");

                    this.flowPane.getChildren().add(node);

                    System.out.println(QuizModel.questionCount());
                }catch (IOException e){
                    System.out.println(e.getMessage());

                }
            }

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }



    }
}
