package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class WikiParser {
    public String parse(InputStream testDataStream) throws IOException {
        JSONArray wikiUserTest = JsonPath.read(testDataStream, "$..user");

        return wikiUserTest.get(0).toString();
    }
}
