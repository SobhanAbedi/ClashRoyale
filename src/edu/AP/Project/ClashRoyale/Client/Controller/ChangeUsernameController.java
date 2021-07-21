package edu.AP.Project.ClashRoyale.Client.Controller;
import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
        import javafx.scene.control.Hyperlink;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;

public class ChangeUsernameController {
    private Client client;
    public ChangeUsernameController(Client client){
        this.client = client;
    }

    @FXML
    private TextField usernameTxt;

    @FXML
    private Hyperlink backBtn;

    @FXML
    private JFXButton changeBtn;

    @FXML
    private Label msgBox;

    @FXML
    private Label serverResponse;

    @FXML
    void Back(ActionEvent event) {
        client.changeScene("Views/Profile.fxml" , new ProfileController(client));
    }

    @FXML
    void change(ActionEvent event) {
// TODO change username in server
        msgBox.setText("Username Changed");
    }

}