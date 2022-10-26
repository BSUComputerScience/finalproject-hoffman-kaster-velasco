package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Main scryTutor = new Main();

        System.out.println("Please enter the name of a Magic: The Gathering card");

        ColorFormatter colorFormatter = new ColorFormatter();
        Scanner userInputScanner = new Scanner(System.in);
        String userInputtedCardName = userInputScanner.nextLine();

        Card cardInfo = scryTutor.getCardInfo(userInputtedCardName);
        //---VERSION of this using formatter class instead---
        //String formattedCardData = ScryfallFormatter.formatJson(new Card[]{cardInfo});
        //System.out.println("\n" + formattedCardData);
    }
    Card getCardInfo(String userInputtedCardName) throws IOException {
        URL encodedURL = ScryfallReader.encodeURL(userInputtedCardName);
        InputStream cardDataStream = ScryfallReader.getScryfallStream(encodedURL);
        ScryfallParser scryfallParser = new ScryfallParser();
        return scryfallParser.parse(cardDataStream);

    }
}
