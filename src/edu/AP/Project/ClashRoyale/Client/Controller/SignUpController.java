package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Client;
import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstructionKind;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstructionKind;
import edu.AP.Project.ClashRoyale.Model.PlayerInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SignUpController {
    private Client client;

    public SignUpController(Client client){
        this.client = client;
    }

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
    private Label serverResponse;

    @FXML
    private Label msgBox;


    @FXML
    void Login(ActionEvent event) {
        client.changeScene("Views/login.fxml" , new LoginController(client));
    }

    @FXML
    void SignUp(ActionEvent event) {
        if (PasswordTxt.getText().equalsIgnoreCase(PasswordTxtRepeat.getText())){
//            TODO Check and Sign up
            ServerInstruction serverInstruction = new ServerInstruction(ServerInstructionKind.SIGNUP,usernameTxt.getText() , PasswordTxt.getText());


            ClientInstruction clientInstruction = client.getClientHandler().signupCheck(serverInstruction);
            if (clientInstruction.getKind() == ClientInstructionKind.SUCCESS){
                client.setUsername(usernameTxt.getText());
                msgBox.setText("Successfully signed up");
                ServerInstruction serverInstruction1 = new ServerInstruction(ServerInstructionKind.GET_PLAYER_INFO,clientInstruction.getArg(0),true);
                ClientInstruction clientInstruction1 = client.getClientHandler().getPlayerInfo(serverInstruction1);
                if (clientInstruction1.getKind() == ClientInstructionKind.USER_INFO){
                    PlayerInfo playerInfo = (PlayerInfo) clientInstruction1.getArg(0);
                    client.setPlayerInfo(playerInfo);
                    System.out.println(client.getPlayerInfo().getUsername() + " " + client.getUsername());
                }else{
                    serverResponse.setText((String) clientInstruction1.getArg(0));
                }
                client.changeScene("Views/battle.fxml" , new BattleController(client));
            }else {
                msgBox.setText((String) clientInstruction.getArg(0));
            }

        }else{
            msgBox.setText("Password Fields are NOT the same.");
        }
    }

}
