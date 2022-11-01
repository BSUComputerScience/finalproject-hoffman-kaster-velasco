package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Main scryTutor = new Main();
        boolean isUserInputEmpty = false;


        do {
            System.out.println("Please enter the name of a Magic: The Gathering card");
            Scanner userInputScanner = new Scanner(System.in);
            String userInputtedCardName = userInputScanner.nextLine();
            if (userInputtedCardName.isEmpty()) {
                System.out.println("Nothing entered. Please try again");
                isUserInputEmpty = true;
            } else {
                Card cardInfo = scryTutor.getCardInfo(userInputtedCardName);

                System.out.println("Card name: " + cardInfo.getCardName());

                String formattedCardData = ScryfallFormatter.formatJson(new Card[]{cardInfo});

                System.out.println("\n" + formattedCardData);
            }
        }
        while (isUserInputEmpty) ;
    }

    Card getCardInfo(String userInputtedCardName) throws IOException {
        URL encodedURL = ScryfallReader.encodeURL(userInputtedCardName);
        InputStream cardDataStream = ScryfallReader.getScryfallStream(encodedURL);
        ScryfallParser scryfallParser = new ScryfallParser();
        return scryfallParser.parse(cardDataStream);

    }
}
