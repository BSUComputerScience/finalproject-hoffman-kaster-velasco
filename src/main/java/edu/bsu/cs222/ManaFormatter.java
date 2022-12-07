package edu.bsu.cs222;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        String costAndRemoveLeftBracket = green.replace("{", " ");
        String removeRightBracket = costAndRemoveLeftBracket.replace("}", ",");
        return removeRightBracket.substring(0, removeRightBracket.length()-1);

    }
}