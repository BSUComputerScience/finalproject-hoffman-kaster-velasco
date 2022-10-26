package edu.bsu.cs222;

import java.io.IOException;


public class ScryfallFormatter {
    public static String formatJson(Card[] cardList) throws IOException {

        String formattedCard = "";
        ColorFormatter colorFormatter = new ColorFormatter();

        if (cardList[0].getCardFlavorText().isBlank()) {
            formattedCard = formattedCard + "Converted Mana Cost:" + colorFormatter.format(cardList[0].getConvertedManaCost())
                    + "\n" + "Type: " + cardList[0].getCardType()
                    + "\n" + "Rarity: " + cardList[0].getCardRarity()
                    + "\n" + "Abilities: " + cardList[0].getCardAbilities()
                    + "\n" + "Power: " + cardList[0].getCardPower()
                    + "\n" + "Toughness: " + cardList[0].getCardToughness()
                    + "\n" + "Colors: " + colorFormatter.format(cardList[0].getCardColors());
        }else{
            formattedCard = formattedCard + "Converted Mana Cost:" + colorFormatter.format(cardList[0].getConvertedManaCost())
                    + "\n" + "Type: " + cardList[0].getCardType()
                    + "\n" + "Rarity: " + cardList[0].getCardRarity()
                    + "\n" + "Abilities: " + cardList[0].getCardAbilities()
                    + "\n" + "Flavor Text: " + cardList[0].getCardFlavorText()
                    + "\n" + "Power: " + cardList[0].getCardPower()
                    + "\n" + "Toughness: " + cardList[0].getCardToughness()
                    + "\n" + "Colors: " + colorFormatter.format(cardList[0].getCardColors());
        }
        System.out.println(formattedCard);
        return formattedCard;
    }
}