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




public class LoginController {
    private Client client;

    /**
     * constructor
     * @param client to access common data
     */
    public LoginController(Client client){
        this.client = client;
    }

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

    /**
     * Sign up Page
     * @param event action evnet
     */
    @FXML
    void SignUpFunc(ActionEvent event) {
        client.changeScene("Views/SignUp.fxml" , new SignUpController(client));
    }

    /**
     * login
     * @param event action event
     */
    @FXML
    void login(ActionEvent event) {
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

        ClientInstruction clientInstruction = client.getClientHandler().loginCheck(serverInstruction);
        if (clientInstruction.getKind() == ClientInstructionKind.SUCCESS){
            client.setUsername(usernameTxt.getText());
            msgBox.setText("Loged in Successfully");
            ServerInstruction serverInstruction1 = new ServerInstruction(ServerInstructionKind.GET_PLAYER_INFO,clientInstruction.getArg(0),false);
            ClientInstruction clientInstruction1 = client.getClientHandler().getPlayerInfo(serverInstruction1);
            if (clientInstruction1.getKind() == ClientInstructionKind.USER_INFO){
                PlayerInfo playerInfo = (PlayerInfo) clientInstruction1.getArg(0);
                client.setPlayerInfo(playerInfo);
                System.out.println(playerInfo);
            }else{
                serverResponse.setText((String) clientInstruction1.getArg(0));
            }
            client.changeScene("Views/Battle.fxml" , new BattleController(client));
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