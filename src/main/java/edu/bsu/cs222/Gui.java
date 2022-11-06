package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
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
    Image cardImg;
    Text blahbbage;

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
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
        grid.add(checkButton, 2, 1);
        correctCardName = new Text();
        correctCardName.setFont(Font.font("Consolas",FontWeight.BOLD,45));
        grid.add(correctCardName,1,6);
        cardAttributes = new Text();
        cardAttributes.setFont(Font.font("Consolas",FontWeight.NORMAL,15));
        cardAttributes.setWrappingWidth(700);
        grid.add(cardAttributes,1,7);

        ImageView imgView = new ImageView(cardImg);
        grid.add(imgView,0,8);

        checkButton.setOnAction(actionEvent -> {
            try {
                handleButtonClick();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });


        Scene scene = new Scene(grid, 440, 240);
        stage.setMaximized(true);
        stage.setTitle("ScryTutor - Magic: The Gathering Card Database");
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
            cardImg = new Image(cardInfo.getCardImageLink());
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

}
