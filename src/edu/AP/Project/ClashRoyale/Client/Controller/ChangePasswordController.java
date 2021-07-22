package edu.AP.Project.ClashRoyale.Client.Controller;
import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
        import javafx.scene.control.Hyperlink;
        import javafx.scene.control.Label;
        import javafx.scene.control.PasswordField;

public class ChangePasswordController {


    private Client client;

    /**
     * constructor
     * @param client to reach common date
     */
    public ChangePasswordController(Client client){
        this.client = client;
    }

    @FXML
    private PasswordField newPasswordTxt;

    @FXML
    private PasswordField newPasswordTxtRepeat;

    @FXML
    private Hyperlink backBtn;

    @FXML
    private JFXButton changeBtn;

    @FXML
    private Label msgBox;

    @FXML
    private PasswordField oldPasswordTxt;

    @FXML
    private Label serverResponse;

    /**
     * back to Profile menu
     * @param event action event
     */
    @FXML
    void back(ActionEvent event) {
        client.changeScene("Views/Profile.fxml" , new ProfileController(client));
        client.buttonEnterSound();
    }

    /**
     * change Password
     * @param event action event
     */
    @FXML
    void change(ActionEvent event) {
// TODO change Password in server
        msgBox.setText("Password Changed");
        client.buttonClickSound();
    }

}

