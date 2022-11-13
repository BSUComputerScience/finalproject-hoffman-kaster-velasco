package edu.bsu.cs222;

public class ColorFormatter {
    public String format(String cardColors) {

        if (cardColors.equals("[]")){
            return "";
        }
        String white = cardColors.replace("W" ,"White");
        String black = white.replace("B","Black");
        String blue = black.replace("U", "Blue");
        String red = blue.replace("R","Red");
        String green = red.replace("G","Green");
        return green.replaceAll("[\\[\\]\\{\\}]", "");
    }
}
