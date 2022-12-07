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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    Button searchOne;
    Button searchTwo;
    Button searchThree;
    List<String> searchHistory = new ArrayList<>();

    boolean isLightModeEnabled = false;

    @Override
    public void start(Stage stage) {

        VBox vbox = new VBox();
        WebView browser = new WebView();

        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #373737");


        //Add Magic logo image
        //setLogoImage("magiclogolight");

        //creating and adding all elements to grid
        sceneTitle = new Text("ScryTutor Card Search");
        sceneTitle.setFont(Font.font("Consolas", FontWeight.NORMAL, 20));
        sceneTitle.setFill(Color.WHITE);
        grid.add(sceneTitle, 1, 4, 2, 1);

        description = new Label("Enter Card Name: ");
        description.setFont(Font.font("Consolas", FontWeight.NORMAL, 12));
        description.setTextFill(Color.WHITE);
        grid.add(description, 0, 5);

        cardToCheck = new TextField();
        grid.add(cardToCheck, 1, 5);

        checkButton = new Button("SEARCH");
        checkButton.setFont(Font.font("Consolas"));
        checkButton.setDefaultButton(true);
        grid.add(checkButton, 2, 5);

        darkModeButton = new Button("Light Mode");
        darkModeButton.setFont(Font.font("Consolas"));
        grid.add(darkModeButton, 8, 0);

        cardTitle = new Text();
        grid.add(cardTitle,1,6);

        cardAttributes = new Text();
        cardAttributes.setWrappingWidth(400);
        cardAttributes.setFill(Color.WHITE);
        grid.add(cardAttributes,1,7);

        searchOne = new Button();
        searchOne.setFont(Font.font("Consolas"));
        grid.add(searchOne,3,5);
        searchOne.setVisible(false);

        searchTwo = new Button();
        searchTwo.setFont(Font.font("Consolas"));
        grid.add(searchTwo,3,6);
        searchTwo.setVisible(false);

        searchThree = new Button();
        searchThree.setFont(Font.font("Consolas"));
        grid.add(searchThree,3,7);
        searchThree.setVisible(false);

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
            cardTitle.setFill(Color.FIREBRICK);
            cardTitle.setText("No Card Name Was Entered");


        } else {
            Main scryTutor = new Main();

            Card cardInfo = scryTutor.getCardInfo(userEntry);
            autoSetTextColor(cardTitle);
            cardTitle.setText(cardInfo.getCardName());
            searchHistory.add(0,cardInfo.getCardName());
            String formattedCardAttributes = ScryfallFormatter.formatJson(new Card[]{cardInfo});
            cardAttributes.setText(formattedCardAttributes);
            cardImgUrl = getCardImgUrl();
            Image cardImg = new Image(cardImgUrl);
            ImageView imgView = new ImageView(cardImg);
            imgView.setFitHeight(325);
            imgView.setFitWidth(225);
            grid.add(imgView, 1, 9);

            searchHistorySizeChecker();
            searchOne.setText(searchHistory.get(0));
            searchOne.setVisible(true);
            searchTwo.setVisible(false);
            searchThree.setVisible(false);
            if (searchHistory.size()==2){
                searchTwo.setText(searchHistory.get(1));
                searchTwo.setVisible(true);
            }
            if (searchHistory.size()==3){
                searchTwo.setText(searchHistory.get(1));
                searchThree.setText(searchHistory.get(2));
                searchTwo.setVisible(true);
                searchThree.setVisible(true);
            }

            searchOne.setOnAction(ActionEvent -> {
                try {
                    mostRecentSearchClick();
                } catch (IOException error){
                    showError(error);
                }
            });

            searchTwo.setOnAction(ActionEvent -> {
                try {
                    secondMostRecentSearchClick();
                } catch (IOException error){
                    showError(error);
                }
            });

            searchThree.setOnAction(ActionEvent -> {
                try {
                    leastRecentSearchClick();
                } catch (IOException error){
                    showError(error);
                }
            });

            hpl = new Hyperlink("Go To Store Page");
            hpl.setFont(Font.font("Arial", 14));
            grid.add(hpl, 1, 8);
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
        grid.setStyle("-fx-background-color: #373737");
        //setLogoImage("magiclogolight");
        sceneTitle.setFill(Color.WHITE);
        description.setTextFill(Color.WHITE);
        cardAttributes.setFill(Color.WHITE);
        darkModeButton.setText("Light mode");
        isLightModeEnabled=false;

        if (!cardTitle.getFill().equals(Color.FIREBRICK)) {
            cardTitle.setFill(Color.WHITE);
        }
    }

    private void setLightMode(){
        grid.setStyle("-fx-background-color: WHITE");
        //setLogoImage("magiclogo");
        sceneTitle.setFill(Color.BLACK);
        description.setTextFill(Color.BLACK);
        cardAttributes.setFill(Color.BLACK);
        darkModeButton.setText("Dark mode");
        isLightModeEnabled=true;
        if (!cardTitle.getFill().equals(Color.FIREBRICK)) {
            cardTitle.setFill(Color.BLACK);
        }
    }

    /*
    private void setLogoImage(String imageName){
        //grid.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 0);
        try (InputStream magicLogoFileLightMode = Thread.currentThread().getContextClassLoader().getResourceAsStream("magiclogolight.jpeg");) {
            Image magicLogo = new Image(magicLogoFileLightMode);
            ImageView magicLogoView = new ImageView();
            magicLogoView.setImage(magicLogo);
            magicLogoView.setFitHeight(85);
            magicLogoView.setFitWidth(250);
            GridPane.setHalignment(magicLogoView, HPos.CENTER);
            grid.add(magicLogoView, 1, 0);
        } catch (IOException e) {
            showError(e);
        }
    }
     */

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
        grid.getChildren().removeIf(hpl -> GridPane.getColumnIndex(hpl) == 1 && GridPane.getRowIndex(hpl) == 8);
        grid.getChildren().removeIf(imgView -> GridPane.getColumnIndex(imgView) == 1 && GridPane.getRowIndex(imgView) == 9);
    }

    private void showError(IOException error){
        removeCardData();
        cardTitle.setText(error.getMessage());
        cardTitle.setFill(Color.FIREBRICK);
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

    private void mostRecentSearchClick() throws IOException{
        cardToCheck.setText(searchOne.getText());
        handleButtonClick();
    }

    private void secondMostRecentSearchClick() throws IOException{
        cardToCheck.setText(searchTwo.getText());
        handleButtonClick();
    }

    private void leastRecentSearchClick() throws IOException{
        cardToCheck.setText(searchThree.getText());
        handleButtonClick();
    }

    public void searchHistorySizeChecker(){
        if (searchHistory.size()==4){
            searchHistory.remove(searchHistory.size()-1);
        }
    }

}


