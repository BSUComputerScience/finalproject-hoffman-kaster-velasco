package edu.bsu.cs222;

public class Card {

    private String cardName;
    private String manaCost;
    private String type;
    private String rarity;
    private String abilities;
    private String flavorText;
    private String power;
    private String toughness;
    private String colors;
    private String loyalty;
    private String usd;
    private String imageLink;
    private String storeLink;

    public Card(String cardName, String manaCost, String type, String rarity, String abilities,
                String flavorText, String power, String toughness, String colors, String loyalty, String usd, String imageLink, String storeLink){
        this.cardName = cardName;
        this.manaCost = manaCost;
        this.type = type;
        this.rarity = rarity;
        this.abilities = abilities;
        this.flavorText = flavorText;
        this.power = power;
        this.toughness = toughness;
        this.colors = colors;
        this.loyalty = loyalty;
        this.usd = usd;
        this.imageLink = imageLink;
        this.storeLink = storeLink;
    }

    public String getCardName(){ return cardName; }

    public String getManaCost(){ return manaCost; }

    public String getCardType(){ return type; }

    public String getCardRarity(){ return rarity; }

    public String getCardAbilities(){ return abilities; }

    public String getCardFlavorText(){ return flavorText; }

    public String getCardPower(){ return power; }

    public String getCardToughness(){ return toughness; }

    public String getCardColors(){ return colors; }

    public String getCardLoyalty(){ return loyalty; }

    public String getCardUsd(){ return usd; }

    public String getCardImageLink(){ return imageLink; }

    public String getCardStoreLink(){ return storeLink; }

}
