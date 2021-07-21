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
    private ArrayList<Card> cards;
    private ArrayList<Card> sortedCards;

    /**
     * Constructor
     * @param client client
     */
    public BattleDeckController(Client client){
        this.client = client;
        selected = new ArrayList<>();
        cardsInDeck = new ArrayList<>();
        cards = new ArrayList<>();
        sortedCards = new ArrayList<>();
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

    /**
     * initialize update requirements
     */
    @FXML
    void initialize(){
        cardInitializer();
    }

    /**
     * add Deck cards
     * @param card card to add
     */
    private void addCards(Card card){
        cards.add(card);
    }

    /**
     * sort cards by their Dheck Location
     */
    private void sortCardsByDeckPlace(){
        sortedCards.clear();
        for (int i = 0; i < cardsInDeck.size(); i++) {
            for(Card card: cards){
                if(i == card.getDeckLocation()) {
                    sortedCards.add(card);
                }
            }
        }

    }

    /**
     * show deck cards (add Deck cards to Deck section)
     */
    private void showDeckCards(){
        for (Card card: sortedCards){
//            System.out.println(card.getName() +  card.getDeckLocation());
            addCardToDeck(card);
        }
        cards.clear();

    }

    /**
     * card initiator
     */
    private void cardInitializer(){
        resetDeckCollection();
        ServerInstruction serverInstruction = new ServerInstruction(ServerInstructionKind.GET_ALL_CARDS);
        ClientInstruction clientInstruction = client.getClientHandler().getAllCards(serverInstruction);
        if (clientInstruction.getKind() == ClientInstructionKind.ALL_CARDS){
            Card[] cards = (Card[]) clientInstruction.getArg(0);
            cardsInDeckCounter = 0;
            for (Card card: cards){
                if (card.getDeckLocation()<0){
                    addCardToCollection(card);
                }else{
                    addCards(card);
                    cardsInDeckCounter++;
                    cardsInDeck.add(card.getName());
                }
            }
            sortCardsByDeckPlace();
            showDeckCards();
        }
    }

    /**
     * Reset deck collection to rewrite
     */
    private void resetDeckCollection(){
        deckHBox1.getChildren().clear();
        deckHBox2.getChildren().clear();
        collectionHBox1.getChildren().clear();
        collectionHBox2.getChildren().clear();
        deckCounter = 0;
        collectionCounter = 0;
        cardsInDeck.clear();
    }

    /**
     * add card to deck part
     * @param card card to be added
     */
    private void addCardToDeck(Card card){
        VBox newObject = creatCardView(card);
        if(deckCounter < numberPerHBox){
            deckHBox1.getChildren().add(deckCounter,newObject);
            deckCounter++;
        }else{
            deckHBox2.getChildren().add(deckCounter-numberPerHBox , newObject);
            deckCounter++;
        }
    }

    /**
     * add new card to collection
     * @param card add card to collection
     */
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

    /**
     * get the full relative path of image
     * @param cardName cardName
     * @return
     */
    private String getPath(String cardName){
        return cardRelativePath + cardName.toLowerCase() +".png";
    }

    /**
     * Create card Views
     * @param card card to show 
     * @return VBox to add to Main HBox
     */
    private VBox creatCardView(Card card){
        VBox vBox = new VBox();
        vBox.alignmentProperty().set(Pos.CENTER);
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
                if (deckCounter  < deckCardNumber){
                    if (!card.isInDeck()) {
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
                        if (selected.get(0).isInDeck() || selected.get(1).isInDeck()){
                            if (!selected.get(0).isInDeck() || !selected.get(1).isInDeck()){
                                ServerInstruction serverInstruction;
                                if(selected.get(0).isInDeck()){
                                    serverInstruction = new ServerInstruction(ServerInstructionKind.UPDATE_DECK,selected.get(0).getDeckLocation()+1,selected.get(1).getName());
                                }else {
                                    serverInstruction = new ServerInstruction(ServerInstructionKind.UPDATE_DECK,selected.get(1).getDeckLocation()+1,selected.get(0).getName());
                                }
                                client.getClientHandler().updateDeck(serverInstruction);

                            }

                        }

                    }
                }
                cardInitializer();

                mouseEvent.consume();
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
        return vBox;
    }

    /**
     * Battle Menu Click
     * @param event action event
     */
    @FXML
    void battleClick(ActionEvent event) {
        client.changeScene("./Views/Battle.fxml", new BattleController(client));
    }

    /**
     * battle deck menu
     * @param event action event
     */
    @FXML
    void battleDeckClick(ActionEvent event) {

    }

    /**
     * battle History Menu
     * @param event action event
     */
    @FXML
    void battleHistoryClick(ActionEvent event) {
        client.changeScene("Views/BattleHistory.fxml" , new BattleHistoryController(client));
    }

    /**
     * Profile Menu
     * @param event action event
     */
    @FXML
    void profileClick(ActionEvent event) {
        client.changeScene("Views/Profile.fxml", new ProfileController(client));
    }

    /**
     * mouse entered change button image
     * @param event Mouse event
     */
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

    /**
     * mouse exited change button image
     * @param event Mouse event
     */
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
