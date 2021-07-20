package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Main;
import edu.AP.Project.ClashRoyale.Client.Network.ServerTransmitter;
import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstructionKind;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstructionKind;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static edu.AP.Project.ClashRoyale.Client.Main.*;


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
    private Label serverResponse;

    @FXML
    void SignUpFunc(ActionEvent event) {
        changeScene("Views/SignUp.fxml");
    }

    @FXML
    void login(ActionEvent event) {
//        TODO Login
//        System.out.println(usernameTxt.getText()  + "  " +PasswordTxt.getText());
//        System.out.println(getSocket().isClosed());
        ServerInstruction serverInstruction = new ServerInstruction(ServerInstructionKind.LOGIN,usernameTxt.getText() , PasswordTxt.getText());
//        ServerTransmitter serverTransmitter = new ServerTransmitter(Main.getSocket(),serverInstruction);
//        System.out.println(getSocket().isConnected());
//        Thread t = new Thread(serverTransmitter);
//        t.start();
//        System.out.println("* " + t.isAlive());
////        serverTransmitter.run();
//        System.out.println(getSocket().isConnected());
//
//        System.out.println(getSocket().isClosed());

        ClientInstruction clientInstruction = Main.getClientHandler().loginCheck(serverInstruction);
        if (clientInstruction.getKind() == ClientInstructionKind.SUCCESS){
            setUsername(usernameTxt.getText());
            msgBox.setText("Loged in Successfully");
            changeScene("Views/Battle.fxml");
        }else{
            msgBox.setText((String) clientInstruction.getArg(0));

        }
//        serverResponse.setText("Waiting for Server...");
//
//        setUsername(usernameTxt.getText());
//        msgBox.setText("Loged in Successfully");
//        changeScene("Views/Battle.fxml");

    }

}