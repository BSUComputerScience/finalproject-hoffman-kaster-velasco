package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class ScryfallParser {
    public String parse(InputStream testDataStream) throws IOException {
        JSONArray cardData = JsonPath.read(testDataStream, "$.*");
        JSONArray cardName = JsonPath.read(cardData, "$..name");
        JSONArray cardManaCost = JsonPath.read(cardData, "$..mana_cost");
        JSONArray cardType = JsonPath.read(cardData, "$..set_type");
        JSONArray cardRarity = JsonPath.read(cardData, "$..rarity");
        JSONArray cardAbilities = JsonPath.read(cardData, "$..oracle_text");
        JSONArray cardFlavorText = JsonPath.read(cardData, "$..flavor_text");
        JSONArray cardPower = JsonPath.read(cardData, "$..power");
        JSONArray cardToughness = JsonPath.read(cardData, "$..toughness");
        JSONArray cardColors = JsonPath.read(cardData, "$..colors");
        System.out.println(cardName.get(0));
        return cardName.get(0).toString();
    }
}
