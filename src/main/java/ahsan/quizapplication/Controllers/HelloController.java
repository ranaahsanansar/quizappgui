
package ahsan.quizapplication.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import ahsan.quizapplication.HelloApplication;
import ahsan.quizapplication.Models.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Rana Ahsan Ansar
 */
public class HelloController implements Initializable {


    @FXML
    private TextField adminEmail;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Button adminLoginBtn;
    @FXML
    private TextField stuEmail;
    @FXML
    private PasswordField stuPassword;
    @FXML
    private Button stuLoginBtn;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void adminLogin(ActionEvent event) {
        String email = adminEmail.getText();
        String password = adminPassword.getText();
        if(email.trim().equalsIgnoreCase("root") && password.trim().equalsIgnoreCase("123456")){
            System.out.println("Correct");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminHomeView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) adminLoginBtn.getScene().getWindow(); // Jo Window Open hogi usi ka Stage mill jaye ga
                stage.setTitle("Admin Panel");
                stage.setScene(scene);
//                stage.setFullScreen(true);
                stage.setMaximized(true);

            }catch (IOException e){
                System.out.println(e.getMessage());

            }


        }else{
            Notifications notifications = Notifications.create().title("Failed").text("Incorrect field").position(Pos.TOP_RIGHT);
            notifications.showError();
        }
    }

    @FXML
    private void stuLogin(ActionEvent event) {
        StudentModel authStudent = new StudentModel(this.stuEmail.getText().trim() , this.stuPassword.getText().trim());

        if(authStudent.login()){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StudentHomeView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) adminLoginBtn.getScene().getWindow(); // Jo Window Open hogi usi ka Stage mill jaye ga
                stage.close();
                Stage studentStage = new Stage();
                studentStage.setTitle("Student Panel");
                studentStage.setScene(scene);
                studentStage.show();

            }catch (IOException e){
                System.out.println(e.getMessage());

            }
        }
    }

}
