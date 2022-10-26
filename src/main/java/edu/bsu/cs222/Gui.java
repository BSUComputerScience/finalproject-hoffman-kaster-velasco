package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Gui extends Application{

    Text sceneTitle;
    Label description;
    TextField cardToCheck;
    Button checkButton;
    Text cardAttributes;

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        sceneTitle = new Text("Scry Tutor");
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
        cardAttributes = new Text();
        grid.add(cardAttributes,1,7);

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
        stage.setTitle("Wikipedia Revision Checker");
        stage.setScene(scene);
        stage.show();
    }

    private void handleButtonClick() throws IOException {
        String userEntry = cardToCheck.getText();
        URL scryFallUrl = ScryfallReader.encodeURL(userEntry);
        InputStream scryFallStream = null;
        try {
            scryFallStream = ScryfallReader.getScryfallStream(scryFallUrl);
        }
        catch (IOException e) {
            cardAttributes.setText("Try again when connected to the internet.");
            displayNetworkErrorDialog();
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

    Card getCardInfo(String userInputtedCardName) throws IOException {
        URL encodedURL = ScryfallReader.encodeURL(userInputtedCardName);
        InputStream cardDataStream = ScryfallReader.getScryfallStream(encodedURL);
        ScryfallParser scryfallParser = new ScryfallParser();
        return scryfallParser.parse(cardDataStream);
    }
}
