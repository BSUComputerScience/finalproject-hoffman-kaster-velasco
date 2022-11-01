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

        //Convert JSONArrays to Strings
        String name = cardNameArray.get(0).toString();
        String convertedManaCost = cardManaCostArray.get(0).toString();
        String type = cardTypeArray.get(0).toString();
        String rarity = cardRarityArray.get(0).toString();
        String abilities = cardAbilitiesArray.get(0).toString();
        String power = "";
        String toughness = "";
        String colors = cardColorsArray.get(0).toString();
        String flavorText = "";
        String loyalty = "";

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


        return new Card(name, convertedManaCost, type, rarity, abilities, flavorText, power, toughness, colors, loyalty);

    }
}
