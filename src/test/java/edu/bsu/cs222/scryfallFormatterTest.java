package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class scryfallFormatterTest {

    @Test
    public void ScryfallCardFormatterTest() throws IOException {
        ScryfallFormatter scryfallFormatter = new ScryfallFormatter();
        Card card = new Card("Shorikai, Genesis Engine", "{2}{W}{U}", "Legendary Artifact - Vehicle", "Mythic", "{1}, {T}: Draw two cards, then discard a card. Create a 1/1 colorless Pilot creature token with \\\"This creature crews Vehicles as though its power were 2 greater.\\\"\\nCrew 8 (Tap any number of creatures you control with total power 8 or more: This Vehicle becomes an artifact creature until end of turn.)\\nShorikai, Genesis Engine can be your commander.",
                "", "8", "8", "U, W");
        Card[] cardList = new Card[1];
        cardList[0] = card;
        String formattedTestDataStream = scryfallFormatter.formatJson(cardList);
        Assertions.assertEquals("""
                Shorikai, Genesis Engine
                {2}{W}{U}
                Legendary Artifact - Vehicle
                Mythic
                {1}, {T}: Draw two cards, then discard a card. Create a 1/1 colorless Pilot creature token with \\"This creature crews Vehicles as though its power were 2 greater.\\"\\nCrew 8 (Tap any number of creatures you control with total power 8 or more: This Vehicle becomes an artifact creature until end of turn.)\\nShorikai, Genesis Engine can be your commander.
                                
                8
                8
                U, W""", formattedTestDataStream);
    }
}