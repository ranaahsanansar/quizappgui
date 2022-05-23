/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package ahsan.quizapplication;

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

    private Label label;
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

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void adminLogin(ActionEvent event) {
        String email = adminEmail.getText();
        String password = adminPassword.getText();
        System.out.println("Email: " + email + "Password: " + password);
    }

    @FXML
    private void stuLogin(ActionEvent event) {

    }

}
