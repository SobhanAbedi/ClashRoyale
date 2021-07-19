package edu.AP.Project.ClashRoyale.Client;

import edu.AP.Project.ClashRoyale.Client.Models.*;
import edu.AP.Project.ClashRoyale.Client.State.CardState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    private static Stage stage;
//    private static CardState cardState;
    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Views/login.fxml")));
        primaryStage.setTitle("ClashRoyal");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
        //
//        initializeCards();
        //
    }

    public static void main(String[] args) {
        launch(args);
    }

//    public static ArrayList<Card> getCards(){
//        return cardState.getCards();
//    }

    public static void changeScene(String address){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(address));
        try {
            loader.load();
        } catch (IOException e) {
            System.err.println("Error appeared in loading SignUp FXML");
        }


        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    private static void initializeCards(){
//        cardState = new CardState();
//        cardState.addCard(new Buildings("Inferno" , 5,1,
//                true,"Images/Cards/buildings/inferno.png",
//                0.4f, Target.AIRANDGROUND,6,40,800,400));
//        cardState.addCard(new Buildings("Canon" , 6,1,
//                false,"Images/Cards/buildings/cannon.png",
//                0.8f, Target.GROUND,5.5f,30,380,60));
//        cardState.addCard(new Troops("Barbarians",5,1,
//                false, "Images/Cards/Troops/barbarians.png",
//                1.5f, Speed.Medium,Target.GROUND,1,false,
//                4,300,75));
//        cardState.addCard(new Spells("Rage",3,1,true,
//                "Images/Cards/spells/rage.png","Description" ,
//                5,6));
//        addCardToDeck("../Images/Cards/Troops/archers.png" ,4);
//        addCardToDeck("../Images/Cards/Troops/baby_dragon.png" , 2);
//        addCardToCollection("../Images/Cards/Troops/barbarians.png", 1);
//        addCardToDeck("../Images/Cards/Troops/giant.png", 1);
//        addCardToDeck("../Images/Cards/Troops/mini_pekka.png", 1);
//        addCardToCollection("../Images/Cards/Troops/valkyrie.png", 3);
//        addCardToDeck("../Images/Cards/Troops/wizard.png", 1);
//        addCardToDeck("../Images/Cards/spells/arrows.png", 2);
//        addCardToCollection("../Images/Cards/spells/fireball.png", 2);
//        addCardToDeck("../Images/Cards/spells/rage.png", 1);
//        addCardToDeck("../Images/Cards/buildings/cannon.png", 2);
//        addCardToCollection("../Images/Cards/buildings/inferno.png", 1);
//    }


}