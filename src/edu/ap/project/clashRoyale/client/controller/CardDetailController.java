package edu.ap.project.clashRoyale.client.controller;
import edu.ap.project.clashRoyale.client.Client;
import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.model.forces.*;
import edu.ap.project.clashRoyale.model.instructions.client.ClientInstruction;
import edu.ap.project.clashRoyale.model.instructions.client.ClientInstructionKind;
import edu.ap.project.clashRoyale.model.instructions.server.ServerInstruction;
import edu.ap.project.clashRoyale.model.instructions.server.ServerInstructionKind;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.Objects;

public class CardDetailController {

    private final String cardRelativePath = "../Images/Cards/NoCost/";
    private final String imageRelativePath = "../Images/Cards/Characters/";
    private Client client;
    private Card card;

    /**
     * constructor
     * @param client client to access common Data
     * @param card card to show Its content
     */
    public CardDetailController(Client client , Card card){
        this.client = client;
        this.card = card;
    }

    @FXML
    private VBox cardVBox;

    @FXML
    private VBox cardDetailsVBox;


    /**
     * update card
     */
    public void update(){
        ServerInstruction serverInstruction = new ServerInstruction(ServerInstructionKind.GET_FORCE_INFO, card.getForces()[0]);
        ClientInstruction clientInstruction = client.getClientHandler().getForceInfo(serverInstruction);
        if (clientInstruction.getKind() == ClientInstructionKind.FAIL){
            System.out.println(clientInstruction.getArg(0));
        }
        Force force = null;
        if(clientInstruction.getKind() == ClientInstructionKind.FORCE_INFO){
            force = (Force) clientInstruction.getArg(0);
            String forceName = force.getName();

            ImageView cardImageView = getImageView(getCardPath(forceName));
            ImageView characterImageView = getImageView(getImagePath(forceName));

            cardVBox.getChildren().add(0,cardImageView);
            cardVBox.getChildren().add(1,characterImageView);
            cardDetailsVBox.getChildren().add(1,getLabel("Name: " + forceName));
            cardDetailsVBox.getChildren().add(2,getLabel("Cost: " + card.getCost()));

            if (force.getForceKind() == ForceKind.BUILDING){
                Building building = (Building) force;
                cardDetailsVBox.getChildren().add(3,getLabel("Damage: " + building.getDamage()));
//                cardDetailsVBox.getChildren().add(4,getLabel("Health Gradient: " + building.getHealthGradiant()));
                cardDetailsVBox.getChildren().add(4,getLabel("HP: " + building.getHP()));
                cardDetailsVBox.getChildren().add(5,getLabel("Hit Speed: " + building.getHitSpeed()));
                cardDetailsVBox.getChildren().add(6,getLabel("Life Time: " + building.getLifeTime()));
                cardDetailsVBox.getChildren().add(7,getLabel("Projectile: " + building.getProjectile()));
                cardDetailsVBox.getChildren().add(8,getLabel("Range: " + building.getRange()));
                cardDetailsVBox.getChildren().add(9,getLabel("Target: " + building.getTarget()));


            }
            if (force.getForceKind() == ForceKind.SOLDIER){
                Soldier soldier = (Soldier) force;
                cardDetailsVBox.getChildren().add(3,getLabel("Damage: " + soldier.getDamage()));
                cardDetailsVBox.getChildren().add(4,getLabel("HP: " + soldier.getHP()));
                cardDetailsVBox.getChildren().add(5,getLabel("HitSpeed: " + soldier.getHitSpeed()));
                cardDetailsVBox.getChildren().add(6,getLabel("Speed: " + soldier.getSpeed()));
                cardDetailsVBox.getChildren().add(7,getLabel("Fly: " + soldier.doesFly()));
                cardDetailsVBox.getChildren().add(8,getLabel("Area Splash: " + soldier.isAreaSplash()));
                cardDetailsVBox.getChildren().add(9,getLabel("Projectile: " + soldier.getProjectile()));
                cardDetailsVBox.getChildren().add(10,getLabel("Range: " + soldier.getRange()));
                cardDetailsVBox.getChildren().add(11,getLabel("Target: " + soldier.getTarget()));


            }
            if (force.getForceKind() == ForceKind.SPELL){
                Spell spell = (Spell) force;
                cardDetailsVBox.getChildren().add(3,getLabel("Damage: " + spell.getDamage()));
                cardDetailsVBox.getChildren().add(4,getLabel("Duration: " + spell.getDuration()));
                cardDetailsVBox.getChildren().add(5,getLabel("Radius: " + spell.getRadius()));
                cardDetailsVBox.getChildren().add(6,getLabel("Damage: " + spell.getDamage()));


            }
        }

    }

    /**
     * Create an ImageView
     * @param address address of image
     * @return image view to show in Left VBox
     */
    private ImageView getImageView(String address){
        Image cardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(address)));
        ImageView cardImageView = new ImageView(cardImage);
        cardImageView.setFitWidth(151);
        cardImageView.setFitHeight(180);
        cardImageView.setPreserveRatio(true);
        return cardImageView;
    }

    /**
     * Create label (for description)
     * @param text text to show
     * @return label to show in Right VBox
     */
    private Label getLabel (String text){
        Label label = new Label();
        label.setText(text);
        label.setFont(Font.font("Lilita One", 16));
        label.setTextFill(Paint.valueOf("#c4deff"));
        return label;
    }

    /**
     * create relative path to image card from card Name
     * @param cardName card name
     * @return String of relative path
     */
    private String getCardPath(String cardName){
        return cardRelativePath + cardName.toLowerCase() +".png";
    }

    /**
     * create relative path to image character from card Name
     * @param cardName card name
     * @return String of relative path
     */
    private String getImagePath(String cardName){
        return imageRelativePath + cardName.toLowerCase() +".png";
    }

}
