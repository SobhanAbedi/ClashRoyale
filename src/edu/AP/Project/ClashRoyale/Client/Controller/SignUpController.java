package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static edu.AP.Project.ClashRoyale.Client.Main.changeScene;

public class SignUpController {

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField PasswordTxt;

    @FXML
    private PasswordField PasswordTxtRepeat;

    @FXML
    private Hyperlink loginBtn;

    @FXML
    private JFXButton signUpBTN;

    @FXML
    private Label msgBox;

    @FXML
    private Label serverResponse;

    @FXML
    void Login(ActionEvent event) {
        changeScene("Views/login.fxml");
    }

    @FXML
    void SignUp(ActionEvent event) {
        if (PasswordTxt.getText().equalsIgnoreCase(PasswordTxtRepeat.getText())){
//            TODO Check and Sign up

            msgBox.setText("Successfully signed up");
            changeScene("Views/battle.fxml");
        }else{
            msgBox.setText("Password Fields are NOT the same.");
        }
    }

}
