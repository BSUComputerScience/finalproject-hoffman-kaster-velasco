package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorFormatterTest {

    @Test
    public void ColorFormatterTest(){
        ColorFormatter colorFormatterTest = new ColorFormatter();
        String colorsTest = "\"W\",\"U\",\"B\",\"R\",\"G\"";
        String colorFormatterResult = colorFormatterTest.format(colorsTest);
        Assertions.assertEquals("\"White\",\"Blue\",\"Black\",\"Red\",\"Green\"", colorFormatterResult);


    }
}
