package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Main scryTutor = new Main();

        //System.out.println("Please enter the name of a Magic: The Gathering card");

        System.out.println("Returning info for 'Kamiz, Obscura Oculus'");


        //Scanner userInputScanner = new Scanner(System.in);
        //String userInputtedCardName = userInputScanner.nextLine();
        String testInput = "Kamiz Obscura Oculus:";

        String cardInfo = scryTutor.getCardInfo(testInput);
        System.out.println(cardInfo);



    }
    String getCardInfo(String userInputtedCardName) throws IOException {
        ScryfallReader scryfallReader = new ScryfallReader();
        URL encodedURL = scryfallReader.encodeURL(userInputtedCardName);
        //InputStream cardDataStream = scryfallReader.getScryfallStream(encodedURL);
        System.out.println(encodedURL);
        /*
        ScryfallParser parser = new ScryfallParser();
        Card parsedCardData = parser.parse(cardDataStream);
        String cardName = parsedCardData.getCardName();
        System.out.println(cardName);
        String formattedCardData = ScryfallFormatter.formatJson(new Card[]{parsedCardData});
        return formattedCardData;
        */

        return "";

    }
}
