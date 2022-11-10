package edu.bsu.cs222;

import java.io.IOException;


public class ScryfallFormatter {
    public static String formatJson(Card[] cardList) throws IOException {


        //ArrayList<String> cardAttributes = new ArrayList<>();
        String formattedCard = "";
        ColorFormatter colorFormatter = new ColorFormatter();

        if(cardList[0].getConvertedManaCost().isBlank()){
        } else {
            formattedCard = "Converted Mana Cost:" + colorFormatter.format(cardList[0].getConvertedManaCost());
        }
        if(cardList[0].getCardType().isBlank()) {
        } else {
            formattedCard += "\n" + "Type: " + cardList[0].getCardType();
        }
        if(cardList[0].getCardRarity().isBlank()) {
        } else {
            formattedCard += "\n" + "Rarity: " + cardList[0].getCardRarity();
        }
        if (cardList[0].getCardAbilities().isBlank()) {
        } else {
            formattedCard += "\n" + "Abilities: " + cardList[0].getCardAbilities();
        }
        if (cardList[0].getCardPower().isBlank()){
        } else {
            formattedCard += "\n" + "Power: " + cardList[0].getCardPower();
        }
        if (cardList[0].getCardToughness().isBlank()) {
        } else {
            formattedCard += "\n" + "Toughness: " + cardList[0].getCardToughness();
        }
        if (colorFormatter.format(cardList[0].getCardColors()).isBlank()) {
        } else {
            formattedCard += "\n" + "Colors: " + colorFormatter.format(cardList[0].getCardColors());
        }
        if (cardList[0].getCardLoyalty().isBlank()) {
        } else {
            formattedCard += "\n" + "Loyalty: " + cardList[0].getCardLoyalty();
        }
        if (cardList[0].getCardUsd().isBlank()) {
        } else {
            formattedCard += "\n" + "Price (USD): $" + cardList[0].getCardUsd();
        }
        return formattedCard;
    }
}
