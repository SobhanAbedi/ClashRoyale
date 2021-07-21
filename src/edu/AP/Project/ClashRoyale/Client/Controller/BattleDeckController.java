package edu.AP.Project.ClashRoyale.Client.Controller;

import com.jfoenix.controls.JFXButton;
import edu.AP.Project.ClashRoyale.Client.Client;
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



public class BattleDeckController {
    private int deckCardNumber = 8;
    private int numberPerHBox = 6;
    private int deckCounter = 0;
    private int collectionCounter = 0;
    private Client client;
    private ArrayList<Card> selected;
    private int cardsInDeckCounter;
    private final String cardRelativePath = "../Images/Cards/";
    private ArrayList<String> cardsInDeck;


    public BattleDeckController(Client client){
        this.client = client;
        selected = new ArrayList<>();
        cardsInDeck = new ArrayList<>();
    }



    @FXML
    private ImageView battleImage;

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
    private VBox battleDeckVBox;

    @FXML
    private HBox deckHBox1;

    @FXML
    private HBox deckHBox2;

    @FXML
    private VBox battleCollectionVBox;

    @FXML
    private HBox collectionHBox1;

    @FXML
    private HBox collectionHBox2;

    @FXML
    void initialize(){
//         TODO
//        CardState cardState = new CardState();

//        cardState.addCard(new Buildings("Inferno" , 5,2,
//                true,"../Images/Cards/buildings/inferno.png",
//                0.4f, Target.AIRANDGROUND,6,40,800,400));
//
//        cardState.addCard(new Buildings("Canon" , 6,1,
//                false,"../Images/Cards/buildings/cannon.png",
//                0.8f, Target.GROUND,5.5f,30,380,60));
//
//        cardState.addCard(new Troops("Barbarians",5,3,
//                false, "../Images/Cards/Troops/barbarians.png",
//                1.5f, Speed.Medium,Target.GROUND,1,false,
//                4,300,75));
//
//        cardState.addCard(new Spells("Rage",3,2,true,
//                "../Images/Cards/spells/rage.png","Description" ,
//                5,6));

        cardInitializer();



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
    }
    private void cardInitializer(){
        resetDeckCollection();
        ServerInstruction serverInstruction = new ServerInstruction(ServerInstructionKind.GET_ALL_CARDS);
        ClientInstruction clientInstruction = client.getClientHandler().getAllCards(serverInstruction);
//        System.out.println(clientInstruction.getKind().toString() + " " + clientInstruction.getArg(0).toString() );
        if (clientInstruction.getKind() == ClientInstructionKind.ALL_CARDS){
            Card[] cards = (Card[]) clientInstruction.getArg(0);
//            System.out.println("card Get");
            cardsInDeckCounter = 0;
            for (Card card: cards){
//                System.out.println(card.getName() + card.getDeckLocation());
                if (card.getDeckLocation()<0){
                    addCardToCollection(card);
                }else{
                    addCardToDeck(card);
                    cardsInDeckCounter++;
                    cardsInDeck.add(card.getName());
//                    System.out.println(cardsInDeckCounter);

                }
            }
        }


//        deckCounter = 0;
//        collectionCounter = 0;
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
//        TODO Request server for battleDeck Cards and their specifications
//
//        ArrayList<Card> cards = cardState.getCards();

//        for (CardModel card: cardModels){
//            if (card.isInDeck()){
//                addCardToDeck(card);
//            }else{
//                addCardToCollection(card);
//            }
//        }
    }

    private void resetDeckCollection(){
        deckHBox1.getChildren().clear();
        deckHBox2.getChildren().clear();
        collectionHBox1.getChildren().clear();
        collectionHBox2.getChildren().clear();
        deckCounter = 0;
        collectionCounter = 0;
        cardsInDeck.clear();
//        System.out.println(deckHBox1.getChildren().size());
    }

    private void addCardToDeck(Card card){
        VBox newObject = creatCardView(card);
        if(deckCounter < numberPerHBox){
//            System.out.println("Add to deck deck hbox1  " + deckHBox1.getChildren().size() );
            deckHBox1.getChildren().add(deckCounter,newObject);
            deckCounter++;
        }else{
            deckHBox2.getChildren().add(deckCounter-numberPerHBox , newObject);
            deckCounter++;
        }

    }

    private void addCardToCollection(Card card){
        VBox newObject = creatCardView(card);
        if(collectionCounter < numberPerHBox){
            collectionHBox1.getChildren().add(collectionCounter,newObject);
            collectionCounter++;
        }else{
            collectionHBox2.getChildren().add(collectionCounter-numberPerHBox , newObject);
            collectionCounter++;
        }
    }

    private String getPath(String cardName){
        return cardRelativePath + cardName.toLowerCase() +".png";
    }

    private VBox creatCardView(Card card){
        VBox vBox = new VBox();
        vBox.alignmentProperty().set(Pos.CENTER);
//        System.out.println((getPath(card.getName())));
        Image card1 = new Image (Objects.requireNonNull(getClass().getResourceAsStream(getPath(card.getName()))));
        ImageView card1View = new ImageView();
        card1View.setPreserveRatio(true);
        card1View.setFitWidth(100);
        card1View.setImage(card1);
        boolean isCardSelected = false;
        for (Card cardSelected : selected){
            if (cardSelected.getName().equals(card.getName())){
                isCardSelected = true;
            }
        }
        if (!isCardSelected) {
            card1View.opacityProperty().set(0.8);
        }


        card1View.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                TODO use this handler to identify and change Deck
//                cardInitializer();
                System.out.println( card.getName() + " pressed");
                if (deckCounter  < deckCardNumber){
                    if (!card.isInDeck()) {
//                        System.out.println("Deck Counter " + deckCounter + 1);
                        ServerInstruction serverInstruction = new ServerInstruction(ServerInstructionKind.UPDATE_DECK,deckCounter + 1 , card.getName());
                        client.getClientHandler().updateDeck(serverInstruction);
                    }

                }
                if (deckCounter == deckCardNumber){
                    if(selected.size() >= 2){
                        selected.clear();
                    }
                    selected.add(card);
                    if (selected.size() == 2){
//                        for(Card s: selected){
//                            System.out.println(s.getName() + s.getDeckLocation());
//                        }
                        if (selected.get(0).isInDeck() || selected.get(1).isInDeck()){
                            if (!selected.get(0).isInDeck() || !selected.get(1).isInDeck()){
                                ServerInstruction serverInstruction;
                                if(selected.get(0).isInDeck()){
                                    System.out.println(selected.get(0).getDeckLocation());
                                    serverInstruction = new ServerInstruction(ServerInstructionKind.UPDATE_DECK,selected.get(0).getDeckLocation()+1,selected.get(1).getName());
                                }else {
                                    System.out.println(selected.get(1).getDeckLocation());
                                    serverInstruction = new ServerInstruction(ServerInstructionKind.UPDATE_DECK,selected.get(1).getDeckLocation()+1,selected.get(0).getName());
                                }
                                client.getClientHandler().updateDeck(serverInstruction);

                            }

                        }

                    }
                }

                cardInitializer();


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
                boolean isCardSelected = false;
                for (Card cardSelected : selected){
                    if (cardSelected.getName().equals(card.getName())){
                        isCardSelected = true;
                    }
                }
                if (!isCardSelected) {
                    card1View.opacityProperty().set(0.8);
                }

            }
        });
        Label levelTxt = new Label();
        levelTxt.setText("Level " + client.getPlayerInfo().getLevel());
        levelTxt.setFont(Font.font("Lilita One",20));
        levelTxt.textFillProperty().set(Paint.valueOf("#b5b2ff"));
        vBox.getChildren().addAll(card1View,levelTxt);
//        vBox.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                vBox.opacityProperty().set(0.5);
//            }
//        });
        return vBox;
    }

    @FXML
    void battleClick(ActionEvent event) {
        client.changeScene("./Views/Battle.fxml", new BattleController(client));
    }

    @FXML
    void battleDeckClick(ActionEvent event) {

//        Image image = new Image();
//        hbox.
    }

    @FXML
    void battleHistoryClick(ActionEvent event) {
        client.changeScene("Views/BattleHistory.fxml" , new BattleHistoryController(client));
    }

    @FXML
    void profileClick(ActionEvent event) {
        client.changeScene("Views/Profile.fxml", new ProfileController(client));
    }

    @FXML
    void mouseEntered(MouseEvent event) {
        Image silverButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/silver.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(silverButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(silverButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(silverButtonImage);
    }

    @FXML
    void mouseExited(MouseEvent event) {
        Image grayButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../Images/Button/gray.png")));
        if (event.getSource().equals(battleBtn))
            battleImage.setImage(grayButtonImage);
        if (event.getSource().equals(battleHistory))
            battleHistoryImage.setImage(grayButtonImage);
        if (event.getSource().equals(profileBtn))
            profileImage.setImage(grayButtonImage);

    }

}
