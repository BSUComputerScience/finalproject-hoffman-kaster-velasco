package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.image.Image;
import java.io.IOException;
import java.io.InputStream;

public class Gui extends Application{

    GridPane grid = new GridPane();
    Label description;
    Text sceneTitle;
    TextField cardToCheck;
    Button checkButton;
    Button darkModeButton;
    Text cardTitle;
    Text cardAttributes;
    Hyperlink hpl;
    String cardLink;
    String cardImgUrl;
    boolean isLightModeEnabled = false;

    @Override
    public void start(Stage stage) {

        VBox vbox = new VBox();
        WebView browser = new WebView();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #1c1c1c");


        //Add Magic logo image
        setLogoImage("magiclogolight");

        //creating and adding all elements to grid
        sceneTitle = new Text("ScryTutor Card Search");
        sceneTitle.setFont(Font.font("Consolas", FontWeight.NORMAL, 30));
        GridPane.setHalignment(sceneTitle, Pos.BASELINE_CENTER.getHpos());
        sceneTitle.setFill(Color.WHITE);
        grid.add(sceneTitle, 1, 1);

        description = new Label("Enter Card Name: ");
        description.setFont(Font.font("Consolas", FontWeight.NORMAL, 16));
        description.setTextFill(Color.WHITE);
        grid.add(description, 0, 2);

        cardToCheck = new TextField();
        grid.add(cardToCheck, 1, 2);

        checkButton = new Button("SEARCH");
        checkButton.setFont(Font.font("Consolas"));
        checkButton.setDefaultButton(true);
        grid.add(checkButton, 2, 2);

        darkModeButton = new Button("Light Mode");
        darkModeButton.setFont(Font.font("Consolas"));
        grid.add(darkModeButton, 8, 0);

        cardTitle = new Text();
        cardTitle.setFont(Font.font("Consolas", FontWeight.NORMAL, 24));
        cardTitle.setWrappingWidth(400);
        grid.add(cardTitle,1,3);

        cardAttributes = new Text();
        cardAttributes.setWrappingWidth(400);
        cardAttributes.setFill(Color.WHITE);
        cardAttributes.setFont(Font.font("Consolas", FontWeight.NORMAL, 14));
        grid.add(cardAttributes,1,4);


        checkButton.setOnAction(event -> {
            try {
                handleButtonClick();
            }
            catch (IOException error) {
                showError(error);
            }
        });
        darkModeButton.setOnAction(event -> {
            if (isLightModeEnabled){
                    setDarkMode();
            } else {
                    setLightMode();
            }
        });

        Scene scene = new Scene(grid, 900, 900);
        stage.setMaximized(true);
        stage.setTitle("ScryTutor - Magic: The Gathering Card Database");
        HBox hbox = new HBox(8);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.getChildren().addAll(hbox, browser);
        VBox.setVgrow(browser, Priority.ALWAYS);
        stage.setScene(scene);
        stage.show();
    }

    private void handleButtonClick() throws IOException {
        String userEntry = cardToCheck.getText();
        if (userEntry.isEmpty()) {
            removeCardData();
            cardTitle.setFill(Color.RED);
            cardTitle.setText("No Card Name Was Entered");


        } else {
            Main scryTutor = new Main();

            Card cardInfo = scryTutor.getCardInfo(userEntry);
            autoSetTextColor(cardTitle);
            cardTitle.setText(cardInfo.getCardName());
            String formattedCardAttributes = ScryfallFormatter.formatJson(new Card[]{cardInfo});
            cardAttributes.setText(formattedCardAttributes);
            cardImgUrl = getCardImgUrl();
            Image cardImg = new Image(cardImgUrl);
            ImageView imgView = new ImageView(cardImg);
            imgView.setFitHeight(325);
            imgView.setFitWidth(225);
            grid.add(imgView, 1, 6);
            hpl = new Hyperlink("Go To Store Page");
            hpl.setFont(Font.font("Arial", 14));
            grid.add(hpl, 1, 5);
            hpl.setOnAction(ActionEvent -> {
                try {
                    hyperLinkClick();
                } catch (IOException error) {
                    showError(error);
                }
            });
        }
    }

    private void setDarkMode() {
        grid.setStyle("-fx-background-color: #1c1c1c");
        setLogoImage("magiclogolight");
        sceneTitle.setFill(Color.WHITE);
        description.setTextFill(Color.WHITE);
        cardAttributes.setFill(Color.WHITE);
        darkModeButton.setText("Light mode");
        isLightModeEnabled=false;

        if (!cardTitle.getFill().equals(Color.RED)) {
            cardTitle.setFill(Color.WHITE);
        }
    }

    private void setLightMode(){
        grid.setStyle("-fx-background-color: WHITE");
        setLogoImage("magiclogo");
        sceneTitle.setFill(Color.BLACK);
        description.setTextFill(Color.BLACK);
        cardAttributes.setFill(Color.BLACK);
        darkModeButton.setText("Dark mode");
        isLightModeEnabled=true;
        if (!cardTitle.getFill().equals(Color.RED)) {
            cardTitle.setFill(Color.BLACK);
        }
    }

    private void setLogoImage(String imageName){
        grid.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 0);
        try (InputStream magicLogoFileLightMode = Thread.currentThread().getContextClassLoader().getResourceAsStream(imageName +".png")) {
            Image magicLogo = new Image(magicLogoFileLightMode);
            ImageView magicLogoView = new ImageView();
            magicLogoView.setImage(magicLogo);
            magicLogoView.setFitHeight(50);
            magicLogoView.setFitWidth(180);
            GridPane.setHalignment(magicLogoView, HPos.CENTER);
            grid.add(magicLogoView, 1, 0);
        } catch (IOException e) {
            showError(e);
        }
    }

    private void autoSetTextColor(Text text){
        if (!isLightModeEnabled) {
            text.setFill(Color.WHITE);
        }
        else {
            text.setFill(Color.BLACK);
        }
    }

    public void removeCardData(){
        cardAttributes.setText("");
        grid.getChildren().removeIf(hpl -> GridPane.getColumnIndex(hpl) == 1 && GridPane.getRowIndex(hpl) == 5);
        grid.getChildren().removeIf(imgView -> GridPane.getColumnIndex(imgView) == 1 && GridPane.getRowIndex(imgView) == 6);
    }

    private void showError(IOException error){
        removeCardData();
        cardTitle.setText(error.getMessage());
        cardTitle.setFill(Color.RED);
    }

    private void hyperLinkClick() throws IOException{
        String userEntry = cardToCheck.getText();
        Main scryTutor = new Main();
        Card cardInfo = scryTutor.getCardInfo(userEntry);
        cardLink = cardInfo.getCardStoreLink();
        getHostServices().showDocument(cardLink);
    }

    private String getCardImgUrl() throws IOException{
        String userEntry = cardToCheck.getText();
        Main scryTutor = new Main();
        Card cardInfo = scryTutor.getCardInfo(userEntry);
        return cardInfo.getCardImageLink();
    }

}


