package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManaFormatterTest {

    @Test
    public void ManaFormatTest(){
        ManaFormatter manaFormatter = new ManaFormatter();
        String colorsTest = "{2}{W}{U}";
        String colorFormatterResult = manaFormatter.format(colorsTest);
        Assertions.assertEquals(" 2, White, Blue", colorFormatterResult);


    }
}
