package edu.bsu.cs222;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;

public class ScryfallParser {
    public Card parse(InputStream cardDataStream) throws IOException {
        //Turn inputstream into String for re-use
        String cardData = new String(cardDataStream.readAllBytes());

        //Parse cardData String for attributes, place in JSONArray
        JSONArray cardNameValidityCheckArray = JsonPath.read(cardData, "$..object");

        String cardNameValidityCheck = cardNameValidityCheckArray.get(0).toString();
        System.out.println("Should equal error: " + cardNameValidityCheck);

        String name = null;
        String convertedManaCost = null;
        String type = null;
        String rarity = null;
        String abilities = null;
        String flavorText = null;
        String power = null;
        String toughness = null;
        String colors = null;
        String loyalty = null;
        String usd = null;
        String imageLink = null;
        String storeLink = null;


        if (cardNameValidityCheck == "error") {
            System.out.println("error detected");
            System.exit(3);
        } else {

            JSONArray cardNameArray = JsonPath.read(cardData, "$..name");
            JSONArray cardManaCostArray = JsonPath.read(cardData, "$..mana_cost");
            JSONArray cardTypeArray = JsonPath.read(cardData, "$..type_line");
            JSONArray cardRarityArray = JsonPath.read(cardData, "$..rarity");
            JSONArray cardAbilitiesArray = JsonPath.read(cardData, "$..oracle_text");
            JSONArray cardFlavorTextArray = JsonPath.read(cardData, "$..flavor_text");
            JSONArray cardPowerArray = JsonPath.read(cardData, "$..power");
            JSONArray cardToughnessArray = JsonPath.read(cardData, "$..toughness");
            JSONArray cardColorsArray = JsonPath.read(cardData, "$..colors");
            JSONArray cardLoyaltyArray = JsonPath.read(cardData, "$..loyalty");
            JSONArray cardUSDArray = JsonPath.read(cardData, "$..usd");
            JSONArray cardImageLink = JsonPath.read(cardData, "$..normal");
            JSONArray cardStoreLink = JsonPath.read(cardData, "$..tcgplayer");

            //Convert JSONArrays to Strings

            name = cardNameArray.get(0).toString();
            convertedManaCost = cardManaCostArray.get(0).toString();
            type = cardTypeArray.get(0).toString();
            rarity = cardRarityArray.get(0).toString();
            abilities = cardAbilitiesArray.get(0).toString();
            power = "";
            toughness = "";
            colors = cardColorsArray.get(0).toString();
            flavorText = "";
            loyalty = "";
            usd = cardUSDArray.get(0).toString();
            imageLink = cardImageLink.get(0).toString();
            storeLink = cardStoreLink.get(0).toString();


            //check if inputted card name is valid


            //Only convert FlavorText, power, and toughness to String if there is something in the array
            if (cardFlavorTextArray.isEmpty()) {
            } else {
                flavorText = cardFlavorTextArray.get(0).toString();
            }
            if (cardPowerArray.isEmpty()) {
            } else {
                power = cardPowerArray.get(0).toString();
            }
            if (cardToughnessArray.isEmpty()) {
            } else {
                toughness = cardToughnessArray.get(0).toString();
            }
            if (cardLoyaltyArray.isEmpty()) {
            } else {
                loyalty = cardLoyaltyArray.get(0).toString();
            }


        }
        return new Card(name, convertedManaCost, type, rarity, abilities, flavorText, power, toughness, colors, loyalty, usd, imageLink, storeLink);

    }

}
