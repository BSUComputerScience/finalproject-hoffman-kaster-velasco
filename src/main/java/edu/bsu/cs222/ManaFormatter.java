package edu.bsu.cs222;

public class ManaFormatter {
    public String format(String cardMana) {
        if (cardMana.equals("[]")){
            return "";
        }

        String white = cardMana.replace("W" ,"White");
        String black = white.replace("B","Black");
        String blue = black.replace("U", "Blue");
        String red = blue.replace("R","Red");
        String green = red.replace("G","Green");
        String costAndRemoveLeftBracket = "Mana Cost:" + green.replace("{", " ");
        return costAndRemoveLeftBracket.replace("}", "");

    }
}