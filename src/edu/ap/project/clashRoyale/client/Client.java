package edu.ap.project.clashRoyale.client;

import edu.ap.project.clashRoyale.client.controller.LoginController;
import edu.ap.project.clashRoyale.model.PlayerInfo;
import edu.ap.project.clashRoyale.server.controller.Server;
import edu.ap.project.clashRoyale.server.model.ClientHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class Client extends Application {
    private  Stage stage;
    private  String username;
    private PlayerInfo playerInfo;
    private  Socket socket;
    private  Server server;
    private  ClientHandler clientHandler;
    private  Thread IranServer;

    /**
     * start of application
     * @param primaryStage primary stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        server = new Server();
        IranServer = new Thread(server);
        IranServer.start();
        clientHandler = new ClientHandler(null , server);

        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/login.fxml"));
        loader.setController(new LoginController(this));
        Parent root = loader.load();
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/ClashRoyal.png"))));
        primaryStage.setTitle("ClashRoyal");
        primaryStage.setScene(new Scene(root, 1200, 800));


        primaryStage.setResizable(false);
        primaryStage.show();
        playSound("Background.wav");
//        try {
//            socket = new Socket(GlobalVariables.SERVER_ADDRESS, GlobalVariables.PORT);
//        }catch (IOException io){
//            System.err.println("Error connecting to server");
//        }
        //
//        initializeCards();
        //
    }

    /**
     * get socket
     * @return socket
     */
    public  Socket getSocket(){
        return socket;
    }

    /**
     *
     * @return player Signed in info
     */
    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    /**
     * set player info
     * @param playerInfo to store
     */
    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    /**
     * main
     * @param args input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * get stage
     * @return primary stage
     */
    public  Stage getStage() {
        return stage;
    }

    /**
     * get server
     * @return server
     */
    public Server getServer() {
        return server;
    }

    /**
     * Access to client handler Without Socket
     * @return client handler
     */
    public  ClientHandler getClientHandler() {
        return clientHandler;
    }

    /**
     * access to Server Thread
     * @return server Thread
     */
    public Thread getIranServer() {
        return IranServer;
    }

    /**
     * change scene
     * @param address address of fxml file
     * @param controller related controller
     */
    public void changeScene(String address , Object controller){
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Client.class.getResource(address));
        loader.setController(controller);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();
        } catch (IOException e) {
            System.err.println("Error appeared in loading FXML");
            e.printStackTrace();
            return;
        }

        Parent root = loader.getRoot();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
    }

    /**
     * Play sound
     * @param trackName sound to play
     */
    public static synchronized void playSound(final String trackName) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Client.class.getResourceAsStream("Sounds/" + trackName));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    /**
     * play sound when click
     */
    public void buttonClickSound(){
        playSound("Button_Click.wav");
    }

    /**
     * play sound when Mouse Enter
     */
    public void buttonEnterSound(){
        playSound("Button.wav");
    }

    /**
     * set username after login
     * @param username username
     */
    public  void setUsername(String username){
        this.username = username;
    }

    /**
     * get username
     * @return username
     */
    public  String getUsername(){
        return username;
    }

}