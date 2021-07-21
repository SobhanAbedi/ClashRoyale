package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Client;
import edu.AP.Project.ClashRoyale.Client.Models.CardModel;
import edu.AP.Project.ClashRoyale.Model.Card;
import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Client.ClientInstructionKind;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstructionKind;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Objects;


public class ProfileController {

    private final String cardRelativePath = "../Images/Cards/";

    private int counter = 0;
    private Client client;

    public ProfileController(Client client) {
        this.client = client;
    }

    @FXML
    private ImageView battleImage;

    @FXML
    private ImageView battleDeckImage;

    @FXML
    private ImageView battleHistoryImage;

    @FXML
    private ImageView profileImage;

    @FXML
    private JFXButton battleBtn;

    @FXML
    private JFXButton battleDeckBtn;

    @FXML
    private JFXButton battleHistory;

    @FXML
    private JFXButton profileBtn;

    @FXML
    private ImageView changeUsernameImage;

    @FXML
    private JFXButton changeUsernameBtn;

    @FXML
    private ImageView changePasswordImage;

    @FXML
    private JFXButton changePasswordBtn;

    @FXML
    private ImageView signOutImage;

    @FXML
    private JFXButton signOutBtn;

    @FXML
    private HBox cardDeck;

    @FXML
    private Label username;

    @FXML
    private Label league;

    @FXML
    private Label cup;

    @FXML
    private ImageView leagueImage;

    @FXML
    private void initialize() {
        username.setText(client.getUsername());
//        TODO get League and Cup
        int level = client.getPlayerInfo().getLevel();
        String levelLabel;
        String leagueImageAddress;
        switch (level) {
            case 2:
                leagueImageAddress = "../Images/League/Stone.png";
                levelLabel = "Stone League";
                break;
            case 3:
                leagueImageAddress = "../Images/League/Bronze.png";
                levelLabel = "Bronze League";
                break;
            case 4:
                leagueImageAddress = "../Images/League/Silver.png";
                levelLabel = "Silver League";
                break;
            case 5:
                leagueImageAddress = "../Images/League/Golden.png";
                levelLabel = "Gold League";
                break;
            default:
                leagueImageAddress = "../Images/League/Wooden.png";
                levelLabel = "Wooden League";
        }
        leagueImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(leagueImageAddress))));
        int cups = client.getPlayerInfo().getScore();


        league.setText(levelLabel);
        cup.setText(String.valueOf(cups));


        ServerInstruction serverInstruction = new ServerInstruction(ServerInstructionKind.GET_ALL_CARDS);
        ClientInstruction clientInstruction = client.getClientHandler().getAllCards(serverInstruction);
        if (clientInstruction.getKind() == ClientInstructionKind.ALL_CARDS) {
            Card[] cards = (Card[]) clientInstruction.getArg(0);
            for (Card card : cards) {
                if (card.getDeckLocation() >= 0) {
                    addCardToDeck(card);


                }
            }
        }
//        ArrayList<CardModel> cardModels = new ArrayList<>();
//        cardModels.add(new CardModel(true , 1, "../Images/Cards/Troops/archers.png" , "archer"));
//        cardModels.add(new CardModel(true , 1, "../Images/Cards/Troops/baby_dragon.png","baby_dragon"));
//        cardModels.add(new CardModel(false , 1, "../Images/Cards/Troops/barbarians.png","barbarians"));
//        cardModels.add(new CardModel(true , 1, "../Images/Cards/Troops/giant.png","giant"));
//        cardModels.add(new CardModel(false , 2, "../Images/Cards/Troops/mini_pekka.png" ,"mini_pekka"));
//        cardModels.add(new CardModel(true , 1, "../Images/Cards/Troops/valkyrie.png","valkyrie"));
//        cardModels.add(new CardModel(false , 1, "../Images/Cards/Troops/wizard.png","wizard"));
//        cardModels.add(new CardModel(true , 1, "../Images/Cards/spells/arrows.png","arrows"));
//        cardModels.add(new CardModel(true , 1, "../Images/Cards/spells/fireball.png","fireball"));
//        cardModels.add(new CardModel(true , 1, "../Images/Cards/spells/rage.png","rage"));
//        cardModels.add(new CardModel(false , 1, "../Images/Cards/buildings/cannon.png","cannon"));
//        cardModels.add(new CardModel(true , 1, "../Images/Cards/buildings/inferno.png","inferno"));

//        for (CardModel card: cardModels){
//            if (card.isInDeck()){
//                addCardToDeck(card);
//            }
//        }
    }

    private void addCardToDeck(Card card) {
        VBox newObject = creatCardView(card);
        cardDeck.getChildren().add(counter, newObject);
        counter++;

    }

    private String getPath(String cardName) {
        return cardRelativePath + cardName.toLowerCase() + ".png";
    }


    private VBox creatCardView(Card card) {
        VBox vBox = new VBox();
        vBox.alignmentProperty().set(Pos.CENTER);
//        Image card1 = new Image (Objects.requireNonNull(getClass().getResourceAsStream(address)));
        ImageView card1View = new ImageView();
        card1View.setPreserveRatio(true);
        card1View.setFitWidth(100);
        card1View.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(getPath(card.getName())))));
        card1View.opacityProperty().set(0.8);
        card1View.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                TODO use this handler to identify and change Deck
//                cardInitializer();
                System.out.println(card.getName() + " pressed");

//                mouseEvent.consume();
            }
        });
        card1View.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                card1View.opacityProperty().set(1);
            }
        });
        card1View.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                card1View.opacityProperty().set(0.8);
            }
        });
        Label levelTxt = new Label();
        levelTxt.setText("Level " + client.getPlayerInfo().getLevel());
        levelTxt.setFont(Font.font("Lilita One", 20));
        levelTxt.textFillProperty().set(Paint.valueOf("#b5b2ff"));
        vBox.getChildren().addAll(card1View, levelTxt);
        return vBox;
    }

    @FXML
    void battleClick(ActionEvent event) {
        client.changeScene("./Views/Battle.fxml", new BattleController(client));
    }

    @FXML
    void battleDeckClick(ActionEvent event) {
        client.changeScene("Views/BattleDeck.fxml", new BattleDeckController(client));
    }

    @FXML
    void battleHistoryClick(ActionEvent event) {
        client.changeScene("Views/BattleHistory.fxml", new BattleHistoryController(client));

    }

    @FXML
    void profileClick(ActionEvent event) {
        client.changeScene("Views/Profile.fxml", new ProfileController(client));

    }

    @FXML
    void mouseClicked(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));

        battleImage.setImage(silverButtonImage);
    }

    @FXML
    void mouseEntered(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        Image goldButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/gold.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(silverButtonImage);
        if (event.getSource().equals(changeUsernameBtn))
            changeUsernameImage.setImage(goldButtonImage);
        if (event.getSource().equals(changePasswordBtn))
            changePasswordImage.setImage(goldButtonImage);
        if (event.getSource().equals(signOutBtn))
            signOutImage.setImage(goldButtonImage);
    }

    @FXML
    void mouseExited(MouseEvent event) {
        Image grayButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/gray.png")));
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(grayButtonImage);
        if (event.getSource().equals(battleDeckBtn))
            battleDeckImage.setImage(grayButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(grayButtonImage);
        if (event.getSource().equals(changeUsernameBtn))
            changeUsernameImage.setImage(silverButtonImage);
        if (event.getSource().equals(changePasswordBtn))
            changePasswordImage.setImage(silverButtonImage);
        if (event.getSource().equals(signOutBtn))
            signOutImage.setImage(silverButtonImage);
    }

    @FXML
    void signOut(ActionEvent event) {
        client.setPlayerInfo(null);
        client.setUsername(null);
        client.changeScene("Views/login.fxml", new LoginController(client));
    }

    @FXML
    void changePassword(ActionEvent event) {
        client.changeScene("Views/ChangePassword.fxml" , new ChangePasswordController(client));
    }

    @FXML
    void changeUsername(ActionEvent event) {
        client.changeScene("Views/ChangeUsername.fxml", new ChangeUsernameController(client));
    }
}
