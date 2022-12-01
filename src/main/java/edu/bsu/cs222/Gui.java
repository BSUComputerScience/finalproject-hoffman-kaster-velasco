package edu.bsu.cs222;

import javafx.application.Application;
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

public class Gui extends Application{

    GridPane grid = new GridPane();
    Label description;
    Text sceneTitle;
    TextField cardToCheck;
    Button checkButton;
    ToggleButton darkModeButton;
    Text cardTitle;
    Text cardAttributes;
    Hyperlink hpl;
    String cardLink;
    String cardImgUrl;

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        WebView browser = new WebView();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));


        sceneTitle = new Text("ScryTutor Card Search");
        sceneTitle.setFont(Font.font("Consolas", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        description = new Label("Enter Card Name: ");
        description.setFont(Font.font("Consolas", FontWeight.NORMAL, 12));
        grid.add(description, 0, 1);
        cardToCheck = new TextField();
        grid.add(cardToCheck, 1, 1);
        checkButton = new Button("SEARCH");
        checkButton.setFont(Font.font("Consolas"));
        checkButton.setDefaultButton(true);
        grid.add(checkButton, 1, 4);

        darkModeButton = new ToggleButton("Dark Mode");
        darkModeButton.setFont(Font.font("Consolas"));
        darkModeButton.setSelected(false);
        grid.add(darkModeButton, 3, 0);

        cardTitle = new Text();
        grid.add(cardTitle,1,6);
        cardAttributes = new Text();
        cardAttributes.setWrappingWidth(400);
        grid.add(cardAttributes,1,7);
        checkButton.setOnAction(event -> {
            try {
                handleButtonClick();
            }
            catch (IOException error) {
                showError(error);
                error.printStackTrace();
            }
        });
        darkModeButton.setOnAction(event -> {
            if (darkModeButton.isSelected()){
                setDarkMode();
            } else {
                setLightMode();
            }
        });

        Scene scene = new Scene(grid, 800, 300);
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
            cardAttributes.setText("");

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
            grid.add(imgView, 1, 9);
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

    private void setDarkMode(){
        grid.setBackground(new Background(new BackgroundFill(Color.web("#373737"), CornerRadii.EMPTY, Insets.EMPTY)));
        if (cardTitle.getFill().equals(Color.FIREBRICK)){
            sceneTitle.setFill(Color.WHITE);
            description.setTextFill(Color.WHITE);
            cardAttributes.setFill(Color.WHITE);
        }
        else {
            sceneTitle.setFill(Color.WHITE);
            description.setTextFill(Color.WHITE);
            cardTitle.setFill(Color.WHITE);
            cardAttributes.setFill(Color.WHITE);
        }
    }

    private void setLightMode(){
        grid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        if (cardTitle.getFill().equals(Color.FIREBRICK)){
            sceneTitle.setFill(Color.BLACK);
            description.setTextFill(Color.BLACK);
            cardAttributes.setFill(Color.BLACK);
        }
        else {
            sceneTitle.setFill(Color.BLACK);
            description.setTextFill(Color.BLACK);
            cardTitle.setFill(Color.BLACK);
            cardAttributes.setFill(Color.BLACK);
        }
    }

    private void autoSetTextColor(Text text){
        if (darkModeButton.isSelected()) {
            text.setFill(Color.WHITE);
        }
        else {
            text.setFill(Color.BLACK);
        }
    }

    public void removeCardData(){
        grid.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 8);
        grid.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 9);
    }

    private void showError(IOException error){
        removeCardData();
        cardTitle.setText(error.getMessage());
        cardTitle.setFill(Color.FIREBRICK);
        cardAttributes.setText("");
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


