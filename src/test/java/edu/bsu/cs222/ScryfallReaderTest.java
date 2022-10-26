package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;

public class ScryfallReaderTest {

    @Test
    public void ScryfallURLEncodeTest() {
        ScryfallReader scryfallReaderTest = new ScryfallReader();
        String inputtedCardName = "Kamiz Obscura Oculus";
        URL encodedCardURL = scryfallReaderTest.encodeURL(inputtedCardName);
        Assertions.assertEquals("https://api.scryfall.com/cards/named?fuzzy=Kamiz+Obscura+Oculus", encodedCardURL.toString());
    }
}
