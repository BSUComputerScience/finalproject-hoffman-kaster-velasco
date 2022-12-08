package edu.bsu.cs222;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;


public class ScryfallReader {

    public static InputStream getScryfallStream(URL encodedUrl) throws IOException {
        URLConnection connection = encodedUrl.openConnection();
        connection.setRequestProperty("User-Agent", "ScryfallReader/0.1 sivelasco@bsu.edu");
        try {
            return connection.getInputStream();
        } catch (FileNotFoundException invalidCardError) {
            throw new FileNotFoundException("The card you entered could not be found");
        } catch (UnknownHostException | NoRouteToHostException noInternetError) {
            throw new UnknownHostException("No internet connection. Please check your connection and try again.");
        }
    }

    public static URL encodeURL (String userInputtedCardName) {
        String defaultCharSetUserInput = URLEncoder.encode(userInputtedCardName, Charset.defaultCharset());
        String urlString = String.format("https://api.scryfall.com/cards/named?fuzzy=%s", defaultCharSetUserInput);

        URL url = null;
        try {
            url = new URL(urlString);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}