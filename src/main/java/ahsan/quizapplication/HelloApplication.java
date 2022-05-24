package ahsan.quizapplication;

import ahsan.quizapplication.Models.QuestionModel;
import ahsan.quizapplication.Models.QuizModel;
import ahsan.quizapplication.Models.StudentModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load());
//
//        stage.setTitle("LogIn!");
//        stage.setScene(scene);
//        stage.show();

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.setTitle("Login");
        stage1.show();

        QuizModel.crateTable();
        QuestionModel.crateTable();
        StudentModel.crateTable();

    }

    public static void main(String[] args) {
        launch();
    }
}