package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class WikiParserTest {

    @Test
    public void parserTestWithWikiJSON() throws IOException {
        WikiParser wikiParserTest = new WikiParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wikiresponse.json");
        String parseDataStream = wikiParserTest.parse(testDataStream);
        Assertions.assertEquals("Vorlee", parseDataStream);

    }
}
