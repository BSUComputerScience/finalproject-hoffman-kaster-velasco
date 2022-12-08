package edu.bsu.cs222;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class ScryfallParser {
    public Card parse(InputStream cardDataStream) throws IOException {
        //Turn inputstream into String for re-use
        String cardData = new String(cardDataStream.readAllBytes());

        JSONArray cardNameValidityCheckArray = JsonPath.read(cardData, "$..object");


        String cardNameValidityCheck = cardNameValidityCheckArray.get(0).toString();

        String name;
        String manaCost = "";
        String type = "";
        String rarity = "";
        String abilities = "";
        String flavorText = "";
        String power = "";
        String toughness = "";
        String colors = "";
        String loyalty = "";
        String usd = "";
        String imageLink = "";
        String storeLink = "";

        //Check if card is valid or not first, then parse data.
        if (cardNameValidityCheck.equals("error")) {
            name = "No matching card was found.";
            return new Card(name, manaCost, type, rarity, abilities, flavorText, power, toughness, colors, loyalty, usd, imageLink, storeLink);
        } else {

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
            JSONArray cardImageLink = JsonPath.read(cardData, "$..normal");
            JSONArray cardStoreLink = JsonPath.read(cardData, "$..tcgplayer");
            JSONArray checkIfOnReserveList = JsonPath.read(cardData, "$..reserved");
            boolean isReserved = (boolean) checkIfOnReserveList.get(0);

            //check if card is on reserved list
            JSONArray cardUSDArray = new JSONArray();
            if (isReserved){
                cardUSDArray.add(" N/A. Only available through auction. (Reserved List)");
            }
            else {
                cardUSDArray = JsonPath.read(cardData, "$..usd");
                }











            //create new JSONArrayList to hold all parsed JSONArrays
            ArrayList<JSONArray> cardAttributes = new ArrayList<>();
            Collections.addAll(cardAttributes, cardManaCostArray, cardTypeArray, cardRarityArray, cardAbilitiesArray,
                    cardFlavorTextArray, cardPowerArray, cardToughnessArray, cardColorsArray, cardLoyaltyArray, cardUSDArray, cardImageLink, cardStoreLink);


            //Create Empty StringArrayList to hold converted JSONArrays later
            ArrayList<String> convertedAttributes = new ArrayList<>();

            //Checks all card attributes to see if they exist: leaves blank space if they don't,
            //converts JSONArray to a string if they do exist
            for (int i = 0; i < cardAttributes.size(); i++) {
                if (cardAttributes.get(i).isEmpty()) {
                    convertedAttributes.add("");
                } else {
                    JSONArray currentAttribute = cardAttributes.get(i);
                    String currentAttributeString = currentAttribute.get(0).toString();
                    convertedAttributes.add(currentAttributeString);
                }
            }
            //getting all individual attributes from arraylist
            name = cardNameArray.get(0).toString();
            manaCost = convertedAttributes.get(0);
            type = convertedAttributes.get(1);
            rarity = convertedAttributes.get(2);
            abilities = convertedAttributes.get(3);
            flavorText = convertedAttributes.get(4);
            power = convertedAttributes.get(5).trim();
            toughness = convertedAttributes.get(6);
            colors = convertedAttributes.get(7);
            loyalty = convertedAttributes.get(8);
            usd = convertedAttributes.get(9);
            imageLink = convertedAttributes.get(10);
            storeLink = convertedAttributes.get(11);

            }
            return new Card(name, manaCost, type, rarity, abilities, flavorText, power, toughness, colors, loyalty, usd, imageLink, storeLink);

        }

    }

