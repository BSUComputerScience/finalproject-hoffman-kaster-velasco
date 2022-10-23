package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class ScryfallParser {
    public String parse(InputStream testDataStream) throws IOException {
        JSONArray cardData = JsonPath.read(testDataStream, "$..name");

        return cardData.get(0).toString();
    }
}
