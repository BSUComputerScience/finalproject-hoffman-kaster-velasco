package edu.bsu.cs222;


public class ScryfallFormatter {
    public static String formatJson(Card[] cardList) {

        String formattedCard = "";
        ColorFormatter colorFormatter = new ColorFormatter();
        ManaFormatter manaFormatter = new ManaFormatter();

        if (!cardList[0].getManaCost().isBlank()) {
            formattedCard = "Mana Cost:" + manaFormatter.format(cardList[0].getManaCost());
        }
        if (!cardList[0].getCardType().isBlank()) {
            formattedCard += "\n" + "Type: " + cardList[0].getCardType();
        }
        if (!cardList[0].getCardRarity().isBlank()) {
            formattedCard += "\n" + "Rarity: " + cardList[0].getCardRarity();
        }
        if (!cardList[0].getCardAbilities().isBlank()) {
            formattedCard += "\n" + "Abilities: " + cardList[0].getCardAbilities();
        }
        if (!cardList[0].getCardPower().isBlank()) {
            formattedCard += "\n" + "Power: " + cardList[0].getCardPower();
        }
        if (!cardList[0].getCardToughness().isBlank()) {
            formattedCard += "\n" + "Toughness: " + cardList[0].getCardToughness();
        }
        if (!colorFormatter.format(cardList[0].getCardColors()).isBlank()) {
            formattedCard += "\n" + "Colors: " + colorFormatter.format(cardList[0].getCardColors());
        }
        if (!cardList[0].getCardLoyalty().isBlank()) {
            formattedCard += "\n" + "Loyalty: " + cardList[0].getCardLoyalty();
        }
        if (!cardList[0].getCardUsd().isBlank()) {
            formattedCard += "\n" + "Price (USD): $" + cardList[0].getCardUsd();
        }
        String replaceEmDash = formattedCard.replace("—", "-");
        String replaceMinus = replaceEmDash.replace("−", "-");
        return replaceMinus.trim();
    }
}
