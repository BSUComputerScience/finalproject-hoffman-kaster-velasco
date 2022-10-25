package edu.bsu.cs222;

public class ColorFormatter {
    public String format(String colorsTest) {
        String white = colorsTest.replace("W" ,"White");
        String black = white.replace("B","Black");
        String blue = black.replace("U", "Blue");
        String red = blue.replace("R","Red");
        String green = red.replace("G","Green");
        //String formattedColors = green.replaceAll("[{}]", "");
        String formattedCardColors = green.substring(0, green.length());
        System.out.println(formattedCardColors);
        return formattedCardColors;
    }
}
