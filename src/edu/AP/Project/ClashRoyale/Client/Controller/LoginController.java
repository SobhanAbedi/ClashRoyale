package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import static edu.AP.Project.ClashRoyale.Client.Main.changeScene;


public class LoginController {

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField PasswordTxt;

    @FXML
    private Hyperlink SignUpBtn;

    @FXML
    private JFXButton LoginBTN;

    @FXML
    private Label msgBox;

    @FXML
    void SignUpFunc(ActionEvent event) {
        changeScene("Views/SignUp.fxml");
    }

    @FXML
    void login(ActionEvent event) {
//        TODO Login 
        msgBox.setText("Loged in Successfully");
        changeScene("Views/Battle.fxml");

    }

}