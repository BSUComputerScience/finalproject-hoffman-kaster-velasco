package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Gui extends Application{

    Text sceneTitle;
    Label description;
    TextField cardToCheck;
    Button checkButton;
    Text correctCardName;
    Text cardAttributes;
    Hyperlink hpl;

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        WebView browser = new WebView();
        GridPane grid = new GridPane();
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
        grid.add(checkButton, 1, 4);
        correctCardName = new Text();
        grid.add(correctCardName,1,6);
        cardAttributes = new Text();
        grid.add(cardAttributes,1,7);
        checkButton.setOnAction(actionEvent -> {
            try {
                handleButtonClick();
                hpl = new Hyperlink("Go To Store Page");
                hpl.setFont(Font.font("Arial", 14));
                grid.add(hpl, 1, 10);
                hpl.setOnAction(ActionEvent ->  {
                    try {
                        hyperLinkClick();
                    }
                    catch (IOException i) {
                        i.printStackTrace();
                    }
                });
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });


        Scene scene = new Scene(grid, 440, 240);
        stage.setMaximized(true);
        stage.setTitle("ScryTutor - Magic: The Gathering Card Database");
        HBox hbox = new HBox(8);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        //hbox.getChildren().addAll(hpl);
        vbox.getChildren().addAll(hbox, browser);
        VBox.setVgrow(browser, Priority.ALWAYS);
        stage.setScene(scene);
        stage.show();
    }

    private void handleButtonClick() throws IOException {
        String userEntry = cardToCheck.getText();
        /*
        URL scryFallUrl = ScryfallReader.encodeURL(userEntry);
        InputStream scryFallStream = null;
        try {
            scryFallStream = ScryfallReader.getScryfallStream(scryFallUrl);
        }
        catch (IOException e) {
            cardAttributes.setText("Try again when connected to the internet.");
            displayNetworkErrorDialog();
        }
        */
        if (userEntry.isEmpty()) {
            correctCardName.setText("No Card Name Was Entered");
            cardAttributes.setText("");
        }
        else {
            Main scryTutor = new Main();
            Card cardInfo = scryTutor.getCardInfo(userEntry);
            correctCardName.setText(cardInfo.getCardName());
            String formattedCardAttributes = ScryfallFormatter.formatJson(new Card[]{cardInfo});
            cardAttributes.setText(formattedCardAttributes);

        }

    }

    private void displayNetworkErrorDialog(){
        Stage stage = new Stage();
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid);
        Text error = new Text("Network Connection Error");
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(error, 0, 0, 2, 1);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void hyperLinkClick() throws IOException{
        String userEntry = cardToCheck.getText();
        Main scryTutor = new Main();
        Card cardInfo = scryTutor.getCardInfo(userEntry);
        String formattedCardAttributes = ScryfallFormatter.formatStoreLink(new Card[]{cardInfo});
        getHostServices().showDocument(formattedCardAttributes);
    }

}


