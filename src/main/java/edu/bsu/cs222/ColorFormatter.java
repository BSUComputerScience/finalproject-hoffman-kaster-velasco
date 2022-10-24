package edu.bsu.cs222;

public class ColorFormatter {
    public String format(String colorsTest) {
        String white = colorsTest.replace("{W}","White, ");
        String blue = white.replace("{U}", "Blue, ");
        String black = blue.replace("{B}","Black, ");
        String red = black.replace("{R}","Red, ");
        String green = red.replace("{G}","Green, ");
        String formattedCardColors = green.substring(0, green.length()-2);
        System.out.println(formattedCardColors);
        return formattedCardColors;
    }
}
