package edu.bsu.cs222;

public class Card {

    private String cardName;
    private String convertedManaCost;
    private String type;
    private String rarity;
    private String abilities;
    private String flavorText;
    private String power;
    private String toughness;
    private String colors;
    private String loyalty;

    public Card(String cardName, String cmc, String type, String rarity, String abilities,
                String flavorText, String power, String toughness, String colors, String loyalty){
        this.cardName = cardName;
        this.convertedManaCost = cmc;
        this.type = type;
        this.rarity = rarity;
        this.abilities = abilities;
        this.flavorText = flavorText;
        this.power = power;
        this.toughness = toughness;
        this.colors = colors;
        this.loyalty = loyalty;
    }

    public String getCardName(){ return cardName; }

    public String getConvertedManaCost(){ return convertedManaCost; }

    public String getCardType(){ return type; }

    public String getCardRarity(){ return rarity; }

    public String getCardAbilities(){ return abilities; }

    public String getCardFlavorText(){ return flavorText; }

    public String getCardPower(){ return power; }

    public String getCardToughness(){ return toughness; }

    public String getCardColors(){ return colors; }

    public String getCardLoyalty(){ return loyalty; }

}
