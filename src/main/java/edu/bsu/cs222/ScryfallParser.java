package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;

public class ScryfallParser {
    public String parse(InputStream testDataStream) throws IOException {
        //Turn inputstream into String for re-use
        String cardData = new String(testDataStream.readAllBytes());

        //Parse cardData String for attributes, place in JSONArray
        JSONArray cardNameArray = JsonPath.read(cardData, "$..name");
        JSONArray cardManaCostArray = JsonPath.read(cardData, "$..mana_cost");
        JSONArray cardTypeArray = JsonPath.read(cardData, "$..set_type");
        JSONArray cardRarityArray = JsonPath.read(cardData, "$..rarity");
        JSONArray cardAbilitiesArray = JsonPath.read(cardData, "$..oracle_text");
        JSONArray cardFlavorTextArray = JsonPath.read(cardData, "$..flavor_text");
        JSONArray cardPowerArray = JsonPath.read(cardData, "$..power");
        JSONArray cardToughnessArray = JsonPath.read(cardData, "$..toughness");
        JSONArray cardColorsArray = JsonPath.read(cardData, "$..colors");

        //Convert JSONArrays to Strings
        String name = cardNameArray.get(0).toString();
        String manaCost = cardManaCostArray.get(0).toString();
        String type = cardTypeArray.get(0).toString();
        String rarity = cardRarityArray.get(0).toString();
        String abilities = cardAbilitiesArray.get(0).toString();
        String power = cardPowerArray.get(0).toString();
        String toughness = cardToughnessArray.get(0).toString();
        String colors = cardColorsArray.get(0).toString();
        String flavorText = "";

        //Only convert FlavorText to String if there is something in the cardFlavorTextArray
        if (cardFlavorTextArray.isEmpty()) {
        } else {
            flavorText = cardFlavorTextArray.get(0).toString();
        }
        return name;
    }
}
