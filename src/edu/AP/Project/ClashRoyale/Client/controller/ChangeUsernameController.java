package edu.ap.project.clashRoyale.client.controller;
import com.jfoenix.controls.JFXButton;
import edu.ap.project.clashRoyale.client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
        import javafx.scene.control.Hyperlink;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;

public class ChangeUsernameController {
    private Client client;

    /**
     * Constructor
     * @param client client to reach common data
     */
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

    /**
     * Back to Profile menu
     * @param event action event
     */
    @FXML
    void Back(ActionEvent event) {
        client.changeScene("Views/Profile.fxml" , new ProfileController(client));
        client.buttonEnterSound();
    }

    /**
     * Change Username
     * @param event action event
     */
    @FXML
    void change(ActionEvent event) {
// TODO change username in server
        msgBox.setText("Username Changed");
        client.buttonClickSound();
    }

}