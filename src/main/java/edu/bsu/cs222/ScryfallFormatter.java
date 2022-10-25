package edu.bsu.cs222;

import java.io.IOException;


public class ScryfallFormatter {
    public static String formatJson(Card[] cardList) throws IOException {

        String formattedString = "";

        ColorFormatter colorFormatter = new ColorFormatter();
        formattedString = formattedString + cardList[0].getCardName() + "\n" + colorFormatter.format(cardList[0].getConvertedManaCost()) + "\n"
                + cardList[0].getCardType() + "\n" + cardList[0].getCardRarity() + "\n" + cardList[0].getCardAbilities()
                + "\n" + cardList[0].getCardFlavorText() + "\n" + cardList[0].getCardPower() + "\n" + cardList[0].getCardToughness()
                + "\n" + colorFormatter.format(cardList[0].getCardColors());
        return formattedString;
    }
}