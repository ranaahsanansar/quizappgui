
package ahsan.quizapplication.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        }else{
            System.out.println("Incorret");
        }
    }

    @FXML
    private void stuLogin(ActionEvent event) {

    }

}
