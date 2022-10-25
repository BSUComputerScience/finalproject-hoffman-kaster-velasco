package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class ParserTest {

    @Test
    public void scryfallParserTest() throws IOException {
        ScryfallParser scryfallParser = new ScryfallParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("scryfallParserTestReturn.json");
        Card parseDataStream = scryfallParser.parse(testDataStream);
        Assertions.assertEquals("Kamiz, Obscura Oculus", parseDataStream.getCardName());
    }
}
