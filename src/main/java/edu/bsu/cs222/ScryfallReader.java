package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;


public class ScryfallReader {

    public static InputStream getScryfallStream(URL encodedUrl) throws IOException {
        URLConnection connection = encodedUrl.openConnection();
        connection.setRequestProperty("User-Agent", "ScryfallReader/0.1 sivelasco@bsu.edu");
        return connection.getInputStream();
    }

    public static URL encodeURL (String cardName) {
        String scryfallCardName = (cardName.replaceAll(" ","+"));
        String urlString = String.format("https://api.scryfall.com/cards/named?fuzzy=%s", scryfallCardName);
        URL url = null;
        try {
            url = new URL(urlString.replaceAll(" ", "%20"));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}